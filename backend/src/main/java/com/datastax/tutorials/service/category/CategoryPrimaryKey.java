package com.datastax.tutorials.service.category;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CategoryPrimaryKey {

    @PrimaryKeyColumn(
            name = "parent_id", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private UUID parentId;

    @PrimaryKeyColumn(
            name = "category_id", 
            ordinal = 2, 
            type = PrimaryKeyType.CLUSTERED, 
            ordering = Ordering.ASCENDING)
    private UUID categoryId;

    /**
     * Getter accessor for attribute 'parent'.
     *
     * @return
     *       current value of 'parent'
     */
    public UUID getParentId() {
        return parentId;
    }

    /**
     * Setter accessor for attribute 'parent'.
     * @param parent
     * 		new value for 'parent '
     */
    public void setParentId(UUID parentId) {
        this.parentId = parentId;
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
