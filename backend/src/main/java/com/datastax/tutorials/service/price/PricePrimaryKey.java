package com.datastax.tutorials.service.price;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PricePrimaryKey {
    
    @PrimaryKeyColumn(name = "product_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String productId;

    @PrimaryKeyColumn(name = "store_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String storeId;

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
}
