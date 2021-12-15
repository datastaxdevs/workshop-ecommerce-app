package com.datastax.tutorials.service.category;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private String name;
    private UUID categoryId;
    private String image;
    private UUID parentId;
    private List<String> products;   
    
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

    public UUID getParentId() {
		return this.parentId;
	}

    public void setParentId(UUID _parentId) {
		this.parentId = _parentId;
	}

    public List<String> getProducts() {
		return this.products;
	}
	
    public void setProducts(List<String> _products) {
		this.products = _products;
	}
	
}
