package ecom;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.shade.com.fasterxml.jackson.core.type.TypeReference;
import org.apache.pulsar.shade.com.fasterxml.jackson.databind.ObjectMapper;

public class EcomOrderProcessor {

	private static final String SERVICE_URL = System.getenv("ASTRA_STREAM_URL");
	private static final String YOUR_PULSAR_TOKEN = System.getenv("ASTRA_STREAM_TOKEN");
	private static final String STREAMING_TENANT = System.getenv("ASTRA_STREAM_TENANT");
	private static final String STREAMING_PREFIX = STREAMING_TENANT + "/default/";
	private static final String SUBSCRIPTION_NAME = "ecom-subscription";
	
	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Usage arguments: verify | pick | ship | complete [readonly] [orderid]");
			System.exit(0);
		} else {
			String command = args[0];
			boolean readOnly = isReadOnly(args);

			String topic = getTopic(command);
			String nextTopic = getNextTopic(command);
			
			PulsarClient client = initializeClient(topic);
			
	        boolean receivedMsg = false;
            Message msg = null;
	        
	        if (readOnly || command.equals("verify")) {
	        	// reader
		        while (!receivedMsg) {
		            // Block for up to 1 second for a message
					try {
						Reader<byte[]> reader = client.newReader()
								.topic(topic)
								.startMessageId(MessageId.earliest)
								.create();
						
						msg = reader.readNext(1, TimeUnit.SECONDS);
						if (msg != null) {
							System.out.println(new String(msg.getData()));
							receivedMsg = true;
						}
						
						reader.close();
					} catch (Exception e) {
						System.out.println(e.toString());
						receivedMsg = true;
					}
		        }
	        } else {
	        	// consumer
	        	while (!receivedMsg) {
	        		try {
	        			Consumer consumer = client.newConsumer()
	        					.topic(topic)
	        					.subscriptionName(SUBSCRIPTION_NAME)
	        					.subscriptionType(SubscriptionType.Exclusive)
	        					.subscribe();
	        			
	        			msg = consumer.receive(1, TimeUnit.SECONDS);
	        			
	        			if (msg != null) {
	        				consumer.acknowledge(msg);
	        		        System.out.println(new String(msg.getData()));
	    					receivedMsg = true;
	        			}
	        			
	        			consumer.close();
	        		} catch (Exception e) {
						System.out.println(e.toString());
						receivedMsg = true;
	        		}
	        	}
	        }

	        try {
		        // serialize orderJSON as an object
		        // OrderRequest objOrder = new Gson().fromJson(msg.getData().toString(), OrderRequest.class);
				ObjectMapper mapper = new ObjectMapper();
				//OrderRequest objOrder = mapper.readValue(msg.getData(), OrderRequest.class);
				Map<String, Object> objOrder 
					= mapper.readValue(msg.getData(), new TypeReference<Map<String,Object>>(){});
	        	UUID userId = UUID.fromString(objOrder.get("user_id").toString());
				UUID orderId = UUID.fromString(objOrder.get("order_id").toString());		

		        // push order to next topic
	        	boolean sentMsg = false;
	
		        if (receivedMsg && !nextTopic.contains("none")) {
		        		        	
		        	while (!sentMsg) {
		        		try {
				            // Create producer on a topic
				            Producer<byte[]> producer = client.newProducer()
				                    .topic("persistent://" + nextTopic)
				                    .create();
			
				            // Send a message to the topic
				            producer.send(msg.getData());
				            sentMsg = true;
				            
				            System.out.println("Pushed order " + orderId.toString() + " to " + nextTopic);
				            
				            //Close the producer
				            producer.close();
		        		} catch (Exception e) {
							System.out.println(e.toString());
		        		}
		        	}	        	
		        }
		        // increment order status
		        updateOrderStatus(orderId,userId,command);
		        
	        } catch (Exception ex) {
	        	System.out.println(ex.toString());
	        }
	        
	        closeClient(client);
		}
	}
	
	private static void updateOrderStatus(UUID orderId, UUID userId, String command) {
		String status = computeStatus(command);
		
		// connect to Astra DB cluster
		
		// update order_by_id status
		
		// update order_by_user status
		
		// insert to order_status_history
		
	}
	
	private static String getTopic(String command) {
		String returnVal = "error";
		
    	switch (command.toLowerCase()) {
    	// starting topic
			case "verify":
				returnVal = "pending-orders2";
				break;
			case "pick":
				returnVal = "pending-orders2";
				break;
			case "ship":
				returnVal = "picked-orders";
				break;
			case "complete":
				returnVal = "shipped-orders";
    	}
    	
    	return STREAMING_PREFIX + returnVal;
	}
	
	private static String getNextTopic(String command) {
		String returnVal = "error";
		
    	switch (command.toLowerCase()) {
    	// next topic
			case "verify":
				returnVal = "none";
				break;
			case "pick":
				returnVal = "picked-orders";
				break;
			case "ship":
				returnVal = "shipped-orders";
				break;
			case "complete":
				returnVal = "completed-orders";
    	}
    	
    	return STREAMING_PREFIX + returnVal;
	}
	
	private static String computeStatus(String command) {
		String returnVal = "PENDING";
		
    	switch (command.toLowerCase()) {
			case "pick":
				returnVal = "PICKED";
				break;
			case "ship":
				returnVal = "SHIPPED";
				break;
			case "complete":
				returnVal = "COMPLETE";
    	}
    	
    	return returnVal;
	}
	
	private static boolean isReadOnly(String args[]) {
		boolean returnVal = false;
		
		for (String arg : args) {
			if (arg.toLowerCase().equals("readonly")) {
				returnVal = true;
				break;
			}
		}
		
		System.out.println("readonly=" + returnVal);
		return returnVal;
	}
	
	private static PulsarClient initializeClient(String topic) {
		System.out.println("topic=" + STREAMING_PREFIX + topic);

		try {
	        // Create client object
	        PulsarClient client = PulsarClient.builder()
	                .serviceUrl(SERVICE_URL)
	                .authentication(
	                    AuthenticationFactory.token(YOUR_PULSAR_TOKEN)
	                )
	                .build();
	       
	        return client;
		} catch (Exception e) {
		    System.out.println(e.toString());
			return null;
		}		
	}
	
	private static void closeClient(PulsarClient client) {
		try {
			// always close the client 
			client.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

}
