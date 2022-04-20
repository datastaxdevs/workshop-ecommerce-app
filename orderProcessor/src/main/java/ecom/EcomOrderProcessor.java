package ecom;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.pulsar.client.api.*;

public class EcomOrderProcessor {

	private static PulsarClient client;
	private static Reader<byte[]> reader;
	private static Consumer<byte[]> consumer;
	
	private static final String SERVICE_URL = System.getenv("ASTRA_STREAM_URL");
	private static final String YOUR_PULSAR_TOKEN = System.getenv("ASTRA_STREAM_TOKEN");
	
	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Usage arguments: verify | pick | ship | complete [readonly] [orderid]");
			System.exit(0);
		} else {
			String topic = getTopic(args[0]);
			boolean readOnly = isReadOnly(args);
			
			initializeConnections(topic,readOnly);
			
	        boolean receivedMsg = false;
            Message<byte[]> msg = null;
	        
	        if (readOnly) {
	        	// reader
		        while (!receivedMsg) {
		            // Block for up to 1 second for a message
					try {
						msg = reader.readNext(1, TimeUnit.SECONDS);
					} catch (Exception e) {
						System.out.println(e.toString());
					}
		
		        }
	        } else {
	        	// consumer
	        	while (!receivedMsg) {
	        		try {
	        			msg = consumer.receive(10, TimeUnit.SECONDS);
	        		} catch (Exception e) {
						System.out.println(e.toString());
					}
	        	}
	        }

	        if(msg != null){
                System.out.printf("Message received: %s%n",  new String(msg.getData()));

                receivedMsg = true;
            }

	        closeConnections(readOnly);
		}
	}
	
	private static String getTopic(String command) {
		String returnVal = "error";
		
    	switch (command.toLowerCase()) {
    	// starting topic
			case "verify":
				returnVal = "pending-orders2";
				break;
			case "pick":
				returnVal = "pending-orders";
				break;
			case "ship":
				returnVal = "picked-orders";
				break;
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
	
	private static void initializeConnections(String topic, boolean readOnly) {
		System.out.println("topic=ecomorders/default/" + topic);
		
		try {
	        // Create client object
	        client = PulsarClient.builder()
	                .serviceUrl(SERVICE_URL)
	                .authentication(
	                    AuthenticationFactory.token(YOUR_PULSAR_TOKEN)
	                )
	                .build();
	
	        if (readOnly) {
		        // Create a reader on a topic
		        reader = client.newReader()
		                .topic("ecomorders/default/" + topic)
		                .startMessageId(MessageId.earliest)
		                .create();
	        } else {
	            // Create a consumer on a topic with a subscription
	            consumer = client.newConsumer()
	                    .topic("ecomorders/default/" + topic)
	                    .subscriptionName(topic + "-sub")
	                    .subscribe();
	        }
	        
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void closeConnections(boolean readOnly) {
		try {
			if (readOnly) {
				reader.close();
			} else {
				consumer.close();
			}
			
			// always close the client 
			client.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

}
