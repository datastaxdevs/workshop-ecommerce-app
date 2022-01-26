package com.datastax.tutorials.service.price;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WebBean to interact with Prices.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
	
	@Size(min=5, max=30)
    @NotNull(message="ProductId cannot be null")
	@JsonProperty("product_id")
    private String productId;
	
	@JsonProperty("store_id")
	@NotNull(message="StoreId cannot be null")
	private String storeId;
	
	@Min(value=0, message="Price must be positive")
	@NotNull(message="Value cannot be null")
	private BigDecimal value;

	/**
	 * Constructor.
	 * 
	 * @param productId
	 *         product identifier
	 * @param storeId
	 *         store identifier
	 * @param value
	 *         price value
	 */
	public Price(@Size(min = 5, max = 30) 
	             @NotNull(message = "ProductId cannot be null") String productId,
	             @NotNull(message = "StoreId cannot be null") String storeId,
	             @Min(value = 0, message = "Price must be positive") 
	             @NotNull(message = "Value cannot be null") BigDecimal value) {
        super();
        this.productId = productId;
        this.storeId = storeId;
        this.value = value;
    }

    /**
     * Default contructor.
     */
    public Price() {}
	
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
     * Getter accessor for attribute 'storeId'.
     *
     * @return
     *       current value of 'storeId'
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * Setter accessor for attribute 'storeId'.
     * @param storeId
     * 		new value for 'storeId '
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /**
     * Getter accessor for attribute 'value'.
     *
     * @return
     *       current value of 'value'
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Setter accessor for attribute 'value'.
     * @param value
     * 		new value for 'value '
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }
	
	
}
