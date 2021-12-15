package com.datastax.tutorials.service.category;

public class CategoryResponse {
    private int count;
    private Category[] data;
    
    public int getCount() {
    	return this.count;
    }
    
    public void setCount(int _count) {
    	this.count = _count;
    }
    
    public Category[] getData() {
    	return this.data;
    }
    
    public void setData(Category[] _data) {
    	this.data = _data;
    }
}
