package com.datastax.tutorials.service.cartproducts;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table("cart_products")
public class CartProductEntity implements Serializable {

	private static final long serialVersionUID = -8746649804590601031L;

	@PrimaryKey
	private CartProductsPrimaryKey key;
	
	@Column("product_description")
	@CassandraType(type = Name.TEXT)
	private String productDesc;
	
	@Column("product_name")
	@CassandraType(type = Name.TEXT)
	private String productName;

	@Column("quantity")
	@CassandraType(type = Name.INT)
	private int quantity;

	public CartProductsPrimaryKey getKey() {
		return key;
	}

	public void setKey(CartProductsPrimaryKey _key) {
		this.key = _key;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String _productDesc) {
		this.productDesc = _productDesc;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String _productName) {
		this.productName = _productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int _quantity) {
		this.quantity = _quantity;
	}

}
