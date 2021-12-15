package com.datastax.tutorials.service.category;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

/** Repository for {@link CategoryEntity}. */
public interface CategoryRepository extends CassandraRepository<CategoryEntity, CategoryPrimaryKey> {
    
    /**
     * Retrieve the list of categories of a parent partition.
     *
     * @param productId
     *      parent
     * @return
     *      list of prices
     */
    List<CategoryEntity> findByKeyParentId(UUID parentId);
    
    /**
     * Find the category if exist.
     *
     * @param parent
     *      parent
     * @param name
     *      name
     * @return
     *      price if it exists in the db
     */
    List<CategoryEntity> findByKeyParentIdAndKeyCategoryId(UUID parentId, UUID categoryId);
    
}
