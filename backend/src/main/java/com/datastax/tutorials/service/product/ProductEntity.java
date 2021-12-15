package com.datastax.tutorials.service.product;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Pojo Mapping the Cassandra class:
 * 
 * CREATE TABLE product (
 *   product_id TEXT,
 *   product_group TEXT,
 *   name TEXT,
 *   brand TEXT,
 *   model_number TEXT,
 *   short_desc TEXT,
 *   long_desc TEXT,
 *   specifications MAP<TEXT,TEXT>,
 *   linked_documents MAP<TEXT,TEXT>,
 *   images SET<TEXT>,
 *   PRIMARY KEY(product_id)
 * );
 */
@Table("product")
public class ProductEntity implements Serializable {
    
    /** Serial. */
    private static final long serialVersionUID = 2835469764140679456L;

    @PrimaryKey("product_id")
    @CassandraType(type = Name.TEXT)
    private String productId;
    
    @Column("brand")
    @CassandraType(type = Name.TEXT)
    private String brand;
    
    @Column("images")
    @CassandraType(type = CassandraType.Name.SET, typeArguments = Name.TEXT)
    private Set<String> images;
    
    @Column("linked_documents")
    @CassandraType(type = CassandraType.Name.MAP, typeArguments = { Name.TEXT, Name.TEXT })
    private Map<String, String> linkedDocuments;
    
    @Column("long_desc")
    @CassandraType(type = Name.TEXT)
    private String longDescription;
    
    @Column("model_number")
    @CassandraType(type = Name.TEXT)
    private String modelNumber;
    
    @Column("name")
    @CassandraType(type = Name.TEXT)
    private String name;
    
    @Column("product_group")
    @CassandraType(type = Name.TEXT)
    private String productGroup;
    
    @Column("short_desc")
    @CassandraType(type = Name.TEXT)
    private String shortDescription;
    
    @Column("specifications")
    @CassandraType(type = CassandraType.Name.MAP, typeArguments = { Name.TEXT, Name.TEXT })
    private Map<String, String> specifications;

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
     * Getter accessor for attribute 'shortDescription'.
     *
     * @return
     *       current value of 'shortDescription'
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Setter accessor for attribute 'shortDescription'.
     * @param shortDescription
     * 		new value for 'shortDescription '
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Getter accessor for attribute 'longDescription'.
     *
     * @return
     *       current value of 'longDescription'
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Setter accessor for attribute 'longDescription'.
     * @param longDescription
     * 		new value for 'longDescription '
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
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
    

}
