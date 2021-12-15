package com.datastax.tutorials.service.price;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Expose Rest Api to interact with prices. 
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
@RequestMapping("/api/v1/prices/")
@Tag(name = "Price Service", description="Provide crud operations for Prices")
public class PriceRestController {
    
    /** Default store id. */
    public static final String DEFAULT_STORE_ID = "web";
    
    /** Inject the repository. */
    private PriceRepository priceRepo;
    
    /**
     * Injection through constructor.
     *  
     * @param repo
     *      repository
     */
    public PriceRestController(PriceRepository repo) {
        this.priceRepo = repo;
    }
    
    /**
     * Retrieve all prices for a product.
     * @param req
     *      current request
     * @param productid
     *      product identifier
     * @return
     *      list of prices
     */
    @GetMapping("/{productid}")
    @Operation(
     summary = "Retrieve prices for a product from its id",
     description= "Find **prices list** for product from its id `SELECT * FROM PRICE WHERE product_id =?`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "A list of prices is provided for the producd",
         content = @Content(
           mediaType = "application/json",
           schema = @Schema(implementation = Price.class, name = "Price")
         )
       ),
       @ApiResponse(
         responseCode = "404", 
         description = "ProductId not found",
         content = @Content(mediaType = "")),
       @ApiResponse(
         responseCode = "400",
         description = "Invalid parameter check productId format."),
       @ApiResponse(
         responseCode = "500",
         description = "Technical Internal error.") 
    })
    public ResponseEntity<Stream<Price>> findAllByProductId(
            HttpServletRequest req, 
            @PathVariable(value = "productid")
            @Parameter(name = "productid", description = "Product identifier", example = "LS5342XL")
            String productid) {
        // Get the partition (be careful unicity is here not ensured
        List<PriceEntity> e = priceRepo.findByKeyProductId(productid);
        if (e.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(e.stream().map(this::mapPrice));
    }
    
    @GetMapping("/price/{productid}")
    @Operation(
        summary = "Retrieve prices for a product and the default store 'web'",
        description= "Find **prices list** for product from its id `SELECT * FROM PRICE WHERE product_id =? and store_id='web'`",
        responses = {
              @ApiResponse(
                responseCode = "200",
                description = "A price for the product in web",
                content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Price.class, name = "Price")
                )
              ),
              @ApiResponse(
                responseCode = "404", 
                description = "ProductId not found"),
              @ApiResponse(
                responseCode = "400",
                description = "Invalid parameter check productId format."),
              @ApiResponse(
                responseCode = "500",
                description = "Technical Internal error.") 
    })    
    public ResponseEntity<Price> findByProductId(HttpServletRequest req, 
            @PathVariable(value = "productid")
            @Parameter(name = "productid", description = "Product identifier", example = "LS5342XL")
            String productid) {
        return findByProductIdAndStoreId(req, productid, DEFAULT_STORE_ID);
    }
            
    @GetMapping("/price/{productid}/{storeId}")
    @Operation(
       summary = "Retrieve price for a product and a store",
       description= "Find Price from a product and a store `SELECT * FROM PRICE WHERE product_id =? and storeId=?`",
       responses = {
         @ApiResponse(
           responseCode = "200",
           description = "A list of prices is provided for the product id",
           content = @Content(
            mediaType = "application/json",
                  schema = @Schema(implementation = Price.class, name = "Price")
                )
              ),
              @ApiResponse(
                responseCode = "404", 
                description = "ProductId not found"),
              @ApiResponse(
                responseCode = "400",
                description = "Invalid parameter check productId or storeid format."),
              @ApiResponse(
                responseCode = "500",
                description = "Technical Internal error.") 
    })
    public ResponseEntity<Price> findByProductIdAndStoreId(HttpServletRequest req, 
            @PathVariable(value = "productid")
            @Parameter(name = "productid", description = "Product identifier", example = "LS5342XL")
            String productid,
            @PathVariable(value = "storeId")
            @Parameter(name = "storeId", description = "Store identifier", example = "web")
            String storeId) {
        Optional<PriceEntity> pe = 
                priceRepo.findByKeyProductIdAndKeyStoreId(productid, storeId);
        return pe.isPresent() ? 
                ResponseEntity.ok(mapPrice(pe.get())) : 
                ResponseEntity.notFound().build();
    }       
     
    /**
     * Mapping Entity => REST.
     *
     * @param p
     *      entity
     * @return
     *      rest bean
     */
    private Price mapPrice(PriceEntity p) {
        Price pr = new Price();
        pr.setProductId(p.getKey().getProductId());
        pr.setStoreId(p.getKey().getStoreId());
        pr.setValue(new BigDecimal(p.getValue()));
        return pr;
    }
    
}
