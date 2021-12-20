package com.datastax.tutorials.service.featured;

import java.math.BigDecimal;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Featured {
	private int featureId;
	private UUID categoryId;
    private String name;
    private String image;
    private UUID parentId;
    private BigDecimal price;   
    
    public int getFeatureId() {
    	return this.featureId;
    }
    
    public void setFeatureId(int _featureId) {
    	this.featureId = _featureId;
    }
    
    public String getName() {
    	return this.name;
	}

    public void setName(String _name) {
		this.name = _name;
	}

    public UUID getCategoryId() {
    	return this.categoryId;
	}

    public void setCategoryId(UUID _categoryId) {
		this.categoryId = _categoryId;
	}

    public String getImage() {
		return this.image;
	}

    public void setImage(String _image) {
		this.image = _image;
	}

    public BigDecimal getPrice() {
    	return this.price;
    }
    
    public void setPrice(BigDecimal _price) {
    	this.price = _price;
    }

    public UUID getParentId() {
    	return this.parentId;
	}

    public void setParentId(UUID _parentId) {
		this.parentId = _parentId;
	}
}
