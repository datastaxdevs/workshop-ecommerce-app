package com.datastax.tutorials.service.featured;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
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
@RequestMapping("/api/v1/featured/")
@Tag(name = "Featured Service", description="Provide crud operations for Featured Products")
public class FeaturedRestController {
    /** Inject the repository. */
    private FeaturedRepository featuredRepo;

    /**
     * Injection through constructor.
     *  
     * @param repo
     *      repository
     */
    public FeaturedRestController(FeaturedRepository repo) {
        this.featuredRepo = repo;
    }

    /**
     * Retrieve all featured products from a feature_id.
     * 
     * @param req
     *      current request
     * @param feature id
     *      feature identifier
     * @return
     *      list of featured products
     */
    @GetMapping("/{featureid}")
    @Operation(
     summary = "Retrieve featured products from a feature_id",
     description= "Find **a list of products** from a feature from its id `SELECT * FROM featured_product_groups WHERE feature_id =?`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "A list of featured products is provided for the feature id",
         content = @Content(
           mediaType = "application/json",
           schema = @Schema(implementation = Featured.class, name = "Featured")
         )
       ),
       @ApiResponse(
         responseCode = "404", 
         description = "Feature Id not found",
         content = @Content(mediaType = "")),
       @ApiResponse(
         responseCode = "400",
         description = "Invalid parameter check featureId format."),
       @ApiResponse(
         responseCode = "500",
         description = "Technical Internal error.") 
    })
    public ResponseEntity<Stream<Featured>> findFeaduredFromFeatureId(
            HttpServletRequest req, 
            @PathVariable(value = "featureid")
            @Parameter(name = "featureid", description = "Featured products identifier", example = "202112")
            int featureId) {
        // Get the partition (be careful unicity is here not ensured
        List<FeaturedEntity> e = featuredRepo.findByKeyFeatureId(featureId);
        if (e.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(e.stream().map(this::mapFeatured));
    }
    
    /**
     * Mapping Entity => REST.
     *
     * @param p
     *      entity
     * @return
     *      rest bean
     */
    private Featured mapFeatured(FeaturedEntity f) {
    	Featured fe = new Featured();
    	fe.setFeatureId(f.getKey().getFeatureId());
    	fe.setCategoryId(f.getKey().getCategoryId());
    	fe.setName(f.getName());
    	fe.setImage(f.getImage());
    	fe.setParentId(f.getParentId());
    	fe.setPrice(new BigDecimal(f.getPrice()));
        return fe;
    }
}
