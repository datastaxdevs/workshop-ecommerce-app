package com.datastax.tutorials.service.product;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Optional;

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
        methods = {POST,GET,OPTIONS,PUT,DELETE,PATCH}, 
        maxAge = 3600, 
        allowedHeaders = {"x-requested-with","origin","content-type","accept"}, 
        origins = "*")
@Tag(   name = "Product Service", 
        description = "Provide crud operations for Products")
@RequestMapping("/api/v1/products/")
public class ProductRestController {

    /** Inject the repository. */
    private ProductRepository productRepo;
    
    /**
     * Injection through constructor.
     *  
     * @param repo
     *      repository
     */
    public ProductRestController(ProductRepository repo) {
        this.productRepo = repo;
    }
    
    @GetMapping("/product/{productid}")
    @Operation(summary = "Retrieve product details from its id", 
               description = "Find product detailsfrom its id `SELECT * FROM PRODUCT WHERE product_id =?`", 
               responses = {
            @ApiResponse(responseCode = "200", description = "A product", 
                         content = @Content(mediaType = "application/json", 
                         schema  = @Schema(implementation = Product.class, name = "Product"))),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid parameter check productId format."),
            @ApiResponse(responseCode = "500", description = "Technical Internal error.")}
    )
    public ResponseEntity<Product> findByProductId(HttpServletRequest req,
            @PathVariable(value = "productid") 
            @Parameter(name = "productid", description = "Product identifier", example = "LS5342XL") 
            String productid) {
        Optional<ProductEntity> pe = productRepo.findById(productid);
        return pe.isPresent() ? 
                ResponseEntity.ok(mapProduct(pe.get())) : 
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
    private Product mapProduct(ProductEntity p) {
        Product pr = new Product();
        pr.setProductId(p.getProductId());
        pr.setBrand(p.getBrand());
        pr.setImages(p.getImages());
        pr.setLinkedDocuments(p.getLinkedDocuments());
        pr.setLongDesc(p.getLongDescription());
        pr.setShortDesc(p.getShortDescription());
        pr.setSpecifications(p.getSpecifications());
        pr.setModelNumber(p.getModelNumber());
        pr.setName(p.getName());
        pr.setProductGroup(p.getProductGroup());
         return pr;
    }
   

}
