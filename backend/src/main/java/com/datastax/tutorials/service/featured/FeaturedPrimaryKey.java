package com.datastax.tutorials.service.featured;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class FeaturedPrimaryKey {
    @PrimaryKeyColumn(
            name = "feature_id", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private int featureId;

    @PrimaryKeyColumn(
            name = "category_id", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED, 
            ordering = Ordering.ASCENDING)
    private UUID categoryId;

    /**
     * Getter accessor for attribute 'featured'.
     *
     * @return
     *       current value of 'featured'
     */
    public int getFeatureId() {
        return featureId;
    }

    /**
     * Setter accessor for attribute 'featured'.
     * @param parent
     * 		new value for 'featured '
     */
    public void setFeaturedId(int featureId) {
        this.featureId = featureId;
    }


    /**
     * Getter accessor for attribute 'id'.
     *
     * @return
     *       current value of 'id'
     */
    public UUID getCategoryId() {
        return categoryId;
    }

    /**
     * Setter accessor for attribute 'id'.
     * @param id
     * 		new value for 'id '
     */
    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
