package com.datastax.tutorials.service.product;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Interacting with Product.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	@JsonProperty("product_id")
	private String productId;
	
	@JsonProperty("product_group")
	private String productGroup;
	
	private String name;
	
	private String brand;
	
	@JsonProperty("model_number")
	private String modelNumber;
	
	@JsonProperty("short_desc")
	private String shortDesc;
	
	@JsonProperty("long_desc")
	private String longDesc;
	
	private Map<String, String> specifications;
	
	@JsonProperty("linked_documents")
	private Map<String, String> linkedDocuments;
	
	private Set<String> images;

    /**
     * Getter accessor for attribute 'productId'.
     *
     * @return
     *       current value of 'productId'
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Setter accessor for attribute 'productId'.
     * @param productId
     * 		new value for 'productId '
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Getter accessor for attribute 'productGroup'.
     *
     * @return
     *       current value of 'productGroup'
     */
    public String getProductGroup() {
        return productGroup;
    }

    /**
     * Setter accessor for attribute 'productGroup'.
     * @param productGroup
     * 		new value for 'productGroup '
     */
    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    /**
     * Getter accessor for attribute 'name'.
     *
     * @return
     *       current value of 'name'
     */
    public String getName() {
        return name;
    }

    /**
     * Setter accessor for attribute 'name'.
     * @param name
     * 		new value for 'name '
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter accessor for attribute 'brand'.
     *
     * @return
     *       current value of 'brand'
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter accessor for attribute 'brand'.
     * @param brand
     * 		new value for 'brand '
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter accessor for attribute 'modelNumber'.
     *
     * @return
     *       current value of 'modelNumber'
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * Setter accessor for attribute 'modelNumber'.
     * @param modelNumber
     * 		new value for 'modelNumber '
     */
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    /**
     * Getter accessor for attribute 'shortDesc'.
     *
     * @return
     *       current value of 'shortDesc'
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * Setter accessor for attribute 'shortDesc'.
     * @param shortDesc
     * 		new value for 'shortDesc '
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /**
     * Getter accessor for attribute 'longDesc'.
     *
     * @return
     *       current value of 'longDesc'
     */
    public String getLongDesc() {
        return longDesc;
    }

    /**
     * Setter accessor for attribute 'longDesc'.
     * @param longDesc
     * 		new value for 'longDesc '
     */
    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    /**
     * Getter accessor for attribute 'specifications'.
     *
     * @return
     *       current value of 'specifications'
     */
    public Map<String, String> getSpecifications() {
        return specifications;
    }

    /**
     * Setter accessor for attribute 'specifications'.
     * @param specifications
     * 		new value for 'specifications '
     */
    public void setSpecifications(Map<String, String> specifications) {
        this.specifications = specifications;
    }

    /**
     * Getter accessor for attribute 'linkedDocuments'.
     *
     * @return
     *       current value of 'linkedDocuments'
     */
    public Map<String, String> getLinkedDocuments() {
        return linkedDocuments;
    }

    /**
     * Setter accessor for attribute 'linkedDocuments'.
     * @param linkedDocuments
     * 		new value for 'linkedDocuments '
     */
    public void setLinkedDocuments(Map<String, String> linkedDocuments) {
        this.linkedDocuments = linkedDocuments;
    }

    /**
     * Getter accessor for attribute 'images'.
     *
     * @return
     *       current value of 'images'
     */
    public Set<String> getImages() {
        return images;
    }

    /**
     * Setter accessor for attribute 'images'.
     * @param images
     * 		new value for 'images '
     */
    public void setImages(Set<String> images) {
        this.images = images;
    }

	
}
