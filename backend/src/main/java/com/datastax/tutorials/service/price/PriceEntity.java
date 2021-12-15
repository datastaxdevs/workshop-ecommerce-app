package com.datastax.tutorials.service.price;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

/**
 * CREATE TABLE price (
 *  product_id TEXT,
 *  store_id TEXT,
 *  value DECIMAL,
 *  PRIMARY KEY(product_id,store_id));
 * )
 */
@Table("price")
public class PriceEntity {
    
    @PrimaryKey
    private PricePrimaryKey key;
    
    @Column("value")
    @CassandraType(type = Name.DECIMAL)
    private Double value;

    /**
     * Getter accessor for attribute 'value'.
     *
     * @return
     *       current value of 'value'
     */
    public Double getValue() {
        return value;
    }

    /**
     * Setter accessor for attribute 'value'.
     * @param value
     * 		new value for 'value '
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Getter accessor for attribute 'key'.
     *
     * @return
     *       current value of 'key'
     */
    public PricePrimaryKey getKey() {
        return key;
    }

    /**
     * Setter accessor for attribute 'key'.
     * @param key
     * 		new value for 'key '
     */
    public void setKey(PricePrimaryKey key) {
        this.key = key;
    }

}
