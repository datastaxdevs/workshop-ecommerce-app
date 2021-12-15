package com.datastax.tutorials.service.category;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

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
@RequestMapping("/api/v1/categories/")
@Tag(name = "Category Service", description="Provide crud operations for Category")
public class CategoryRestController {
    
    /** Default store id. */
    public static final String DEFAULT_STORE_ID = "web";
    
    /** Inject the repository. */
    private CategoryRepository catRepo;
    
    /**
     * Injection through constructor.
     *  
     * @param repo
     *      repository
     */
    public CategoryRestController(CategoryRepository repo) {
        this.catRepo = repo;
    }
    
    /**
     * Retrieve all categories from a parent.
     * 
     * @param req
     *      current request
     * @param parent id
     *      category identifier
     * @return
     *      list of categories
     */
    @GetMapping("/{parentid}")
    @Operation(
     summary = "Retrieve Categories for a product from its parentId",
     description= "Find **category list** from a parent from its id `SELECT * FROM category WHERE parent_id =?`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "A list of category is provided for the parent id",
         content = @Content(
           mediaType = "application/json",
           schema = @Schema(implementation = Category.class, name = "Category")
         )
       ),
       @ApiResponse(
         responseCode = "404", 
         description = "Parent not found",
         content = @Content(mediaType = "")),
       @ApiResponse(
         responseCode = "400",
         description = "Invalid parameter check parentId format."),
       @ApiResponse(
         responseCode = "500",
         description = "Technical Internal error.") 
    })
    public ResponseEntity<Stream<Category>> findCategoriesFromParentId(
            HttpServletRequest req, 
            @PathVariable(value = "parentid")
            @Parameter(name = "parentid", description = "Parent identifier", example = "ffdac25a-0244-4894-bb31-a0884bc82aa9")
            UUID parentid) {
        // Get the partition (be careful unicity is here not ensured
        List<CategoryEntity> e = catRepo.findByKeyParentId(parentid);
        if (e.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(e.stream().map(this::mapCategory));
    }
            
    @GetMapping("/category/{parentid}/{categoryid}")
    @Operation(
       summary = "Retrieve category for a parentId and a categoryId",
       description= "Find Category from a product and a store `SELECT * FROM category WHERE parent_id =? and category_id=?`",
       responses = {
         @ApiResponse(
           responseCode = "200",
           description = "the Category is retrieved",
           content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class, name = "Category"))),
         @ApiResponse(
           responseCode = "404", 
           description = "Parent ot name not found"),
         @ApiResponse(
           responseCode = "400",
           description = "Invalid parameter check parent or name format."),
         @ApiResponse(
           responseCode = "500",
           description = "Technical Internal error.") 
    })
    public ResponseEntity<Category> findByParentIdAndCategoryId(HttpServletRequest req, 
            @PathVariable(value = "parentid")
            @Parameter(name = "parentid", description = "Parent identifier", example = "ffdac25a-0244-4894-bb31-a0884bc82aa9")
            UUID parentId,
            @PathVariable(value = "categoryid")
    		@Parameter(name = "categoryid", description = "Category identifier", example = "18105592-77aa-4469-8556-833b419dacf4")
    		UUID categoryId) {
        List<CategoryEntity> categories = catRepo.findByKeyParentIdAndKeyCategoryId(parentId, categoryId);
        if (categories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (categories.size() > 1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(mapCategory(categories.get(0)));
    }       
     
    /**
     * Mapping Entity => REST.
     *
     * @param p
     *      entity
     * @return
     *      rest bean
     */
    private Category mapCategory(CategoryEntity c) {
        Category ca = new Category();
        ca.setParentId(c.getKey().getParentId());
        ca.setCategoryId(c.getKey().getCategoryId());
        ca.setName(c.getName());
        ca.setImage(c.getImage());
        ca.setProducts(c.getProducts());
        return ca;
    }
    
}
