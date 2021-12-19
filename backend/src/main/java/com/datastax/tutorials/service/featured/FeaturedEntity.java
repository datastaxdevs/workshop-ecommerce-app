package com.datastax.tutorials.service.featured;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("featured_product_groups")
public class FeaturedEntity implements Serializable {

	@PrimaryKey
	private FeaturedPrimaryKey key;
	
    @Column("name")
    @CassandraType(type = Name.TEXT)
    private String name;
    
    @Column("image")
    @CassandraType(type = Name.TEXT)
    private String image;

    @Column("parent_id")
    @CassandraType(type = Name.UUID)
    private UUID parentId;

    @Column("price")
    @CassandraType(type = Name.DECIMAL)
    private Double price;
    
    /**
     * Getter accessor for attribute 'key'.
     *
     * @return
     *       current value of 'key'
     */
    public FeaturedPrimaryKey getKey() {
        return key;
    }

    /**
     * Setter accessor for attribute 'key'.
     * @param key
     * 		new value for 'key '
     */
    public void setKey(FeaturedPrimaryKey key) {
        this.key = key;
    }

    /**
     * Getter accessor for attribute 'image'.
     *
     * @return
     *       current value of 'image'
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter accessor for attribute 'image'.
     * @param image
     * 		new value for 'image '
     */
    public void setImage(String image) {
        this.image = image;
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
     * 		new value for 'name'
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter accessor for attribute 'parentId'.
     *
     * @return
     *       current value of 'parentId'
     */
    public UUID getParentId() {
        return parentId;
    }

    /**
     * Setter accessor for attribute 'parentId'.
     * @param parent_id
     * 		new value for 'parentId'
     */
    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    /**
     * Getter accessor for attribute 'price'.
     *
     * @return
     *       current value of 'price'
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Setter accessor for attribute 'price'.
     * @param price
     * 		new value for 'price'
     */
    public void setPrice(Double price) {
        this.price = price;
    }
}
