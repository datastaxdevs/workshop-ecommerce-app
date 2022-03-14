package com.datastax.tutorials.service.order;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Expose Rest API to interact with order data. 
 *
 * @author Cedrick LUNVEN
 * @author Aaron PLOETZ 
 */
@RestController
@CrossOrigin(
  methods = {POST, GET, OPTIONS, PUT, DELETE, PATCH},
  maxAge = 3600,
  allowedHeaders = {"x-requested-with", "origin", "content-type", "accept"},
  origins = "*" 
)
@RequestMapping("/api/v1/order/")
@Tag(name = "Order Service", description="Provide crud operations for Order data")
public class OrderRestController {
	private OrderRepository orderRepo;
	private OrderByUserRepository orderUserRepo;
	
	public OrderRestController(OrderRepository oRepo,OrderByUserRepository oURepo) {
		orderRepo = oRepo;
		orderUserRepo = oURepo;
	}

    /**
     * Retrieve all orders for a user.
     * @param req
     *      current request
     * @param userid
     *      user identifier (UUID)
     * @return
     *      list of orders for that user
     */
    @GetMapping("/{orderid}/")
    @Operation(
     summary = "Retrieve all orders by a userid",
     description= "Find **orderss list** for a user by its id `SELECT * FROM order WHERE orderid =?`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "A list of orders for the user",
         content = @Content(
           mediaType = "application/json",
           schema = @Schema(implementation = Order.class, name = "Order")
         )
       ),
       @ApiResponse(
         responseCode = "404", 
         description = "orderId not found",
         content = @Content(mediaType = "")),
       @ApiResponse(
         responseCode = "400",
         description = "Invalid parameter check orderId format."),
       @ApiResponse(
         responseCode = "500",
         description = "Internal error.") 
    })
    public ResponseEntity<Stream<Order>> findOrderById(
            HttpServletRequest req, 
            @PathVariable(value = "orderid")
            @Parameter(name = "orderid", description = "order identifier (UUID)", example = "5929e846-53e8-173e-8525-80b666c46a83")
            UUID orderid) {
    	Optional<OrderEntity> e = orderRepo.findById(orderid);

    	// map order to entity and return
    	return ResponseEntity.ok(e.stream().map(this::mapOrder));
    }
    
    /**
     * Retrieve all orders for a user.
     * @param req
     *      current request
     * @param userid
     *      user identifier (UUID)
     * @return
     *      list of orders for that user
     */
    @GetMapping("/user/{userid}/")
    @Operation(
     summary = "Retrieve all orders by a userid",
     description= "Find **orders list** for a user by its id `SELECT * FROM order_history WHERE userid =?`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "A list of orders for the user",
         content = @Content(
           mediaType = "application/json",
           schema = @Schema(implementation = Order.class, name = "Order")
         )
       ),
       @ApiResponse(
         responseCode = "404", 
         description = "userId not found",
         content = @Content(mediaType = "")),
       @ApiResponse(
         responseCode = "400",
         description = "Invalid parameter check userId format."),
       @ApiResponse(
         responseCode = "500",
         description = "Internal error.")
    })
    public ResponseEntity<Stream<OrderByUser>> findOrdersByUserId(
            HttpServletRequest req, 
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)", example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {
    	Optional<OrderByUserEntity> e = orderUserRepo.findById(userid);

    	// map order to entity and return
    	return ResponseEntity.ok(e.stream().map(this::mapOrderByUser));
    }

    /**
     * Place an order.
     * @param req
     *      current request
     * @param userid
     *      user identifier (UUID)
     * @return
     *      list of orders for that user
     */
    @PostMapping("/user/{userid}/")
    @Operation(
     summary = "Place an order",
     description= "Create an order for the user id `INSERT INTO order WHERE orderid=?`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "Place an order for the user",
         content = @Content(
           mediaType = "application/json")
       ),
       @ApiResponse(
         responseCode = "404", 
         description = "An error occured",
         content = @Content(mediaType = "")),
       @ApiResponse(
         responseCode = "400",
         description = "Invalid parameter."),
       @ApiResponse(
         responseCode = "500",
         description = "Internal error.")
    })
    @Transactional
    public ResponseEntity<OrderRequest> placeOrder(
            HttpServletRequest req,
            @RequestBody OrderRequest order,
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)", example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {
    	
    	// make sure we have an actual product list
    	if (order.getProductList().size() < 1) {
    		// We get here if the size of the product list is either zero or negative(?)
    		return ResponseEntity.notFound().build();
    	}
    	
    	final String NEW_ORDER_STATUS = "PENDING";
    	
    	// generate order id - version 1 UUID == TimeUUID
    	UUID orderid = Uuids.timeBased();
    	Date orderTimeStamp = new Date(orderid.timestamp());
    	
    	// create DB entry for order (by id)
    	OrderEntity orderE = new OrderEntity();
    	OrderPrimaryKey oKey = new OrderPrimaryKey();
    	oKey.setOrderId(orderid);

    	orderE.setOrderStatus(NEW_ORDER_STATUS);
    	orderE.setOrderSubtotal(order.getOrderSubtotal());
    	orderE.setOrderShippingHandling(order.getOrderShippingHandling());
    	orderE.setOrderTax(order.getOrderTax());
    	orderE.setOrderTotal(order.getOrderTotal());
    	orderE.setPaymentMethod(order.getPaymentMethod());
    	orderE.setShippingAddress(order.getShippingAddress());
    	
    	//set products
    	for (OrderProduct product : order.getProductList()) {
    		oKey.setProductName(product.getProductName());
    		oKey.setProductId(product.getProductId());
        	orderE.setKey(oKey);

    		orderE.setProductQty(product.getProductQty());
    		
        	// save to DB
        	orderRepo.save(orderE);
    	}
    	
    	// create DB entry for order_by_user
    	OrderByUserEntity orderByUserE = new OrderByUserEntity();
    	OrderByUserPrimaryKey oUKey = new OrderByUserPrimaryKey();
    	oUKey.setOrderId(orderid);
    	oUKey.setUserId(userid);
    	orderByUserE.setKey(oUKey);
    	orderByUserE.setOrderStatus(NEW_ORDER_STATUS);
    	orderByUserE.setOrderTotal(order.getOrderTotal());

    	// save to DB
    	orderUserRepo.save(orderByUserE);
    	
    	// TODO Delete existing cart
    	
    	// Adjust request and return it as the response
    	order.setOrderId(orderid);
    	order.setOrderStatus(NEW_ORDER_STATUS);
    	order.setOrderTimestamp(orderTimeStamp);
    	
    	// map order to entity and return
    	return ResponseEntity.ok(order);
    }
    
    private Order mapOrder(OrderEntity entity) {
    	Order order = new Order();
    	
    	// key columns
    	OrderPrimaryKey key = entity.getKey();
    	order.setOrderId(key.getOrderId());
    	order.setProductName(key.getProductName());
    	order.setProductId(key.getProductId());
    	// payload columns
    	order.setProductQty(entity.getProductQty());
    	order.setOrderStatus(entity.getOrderStatus());
    	// not storing timestamp, but deriving it from orderId (TimeUUID)
    	order.setOrderTimestamp(new Date(key.getOrderId().timestamp()));
    	order.setOrderSubtotal(entity.getOrderSubtotal());
    	order.setOrderShippingHandling(entity.getOrderShippingHandling());
    	order.setOrderTax(entity.getOrderTax());
    	order.setOrderTotal(entity.getOrderTotal());
    	order.setPaymentMethod(entity.getPaymentMethod());
    	order.setShippingAddress(entity.getShippingAddress());
    	
    	return order;
    }
 
    private OrderByUser mapOrderByUser(OrderByUserEntity entity) {
    	OrderByUser orderByUser = new OrderByUser();
    	
    	// key columns
    	OrderByUserPrimaryKey key = entity.getKey();
    	orderByUser.setOrderId(key.getOrderId());
    	orderByUser.setUserId(key.getUserId());
    	// payload columns
    	orderByUser.setOrderStatus(entity.getOrderStatus());
    	// not storing timestamp, but deriving it from orderId (TimeUUID)
    	orderByUser.setOrderTimestamp(new Date(key.getOrderId().timestamp()));
    	orderByUser.setOrderTotal(entity.getOrderTotal());
    	
    	return orderByUser;
    }
}
