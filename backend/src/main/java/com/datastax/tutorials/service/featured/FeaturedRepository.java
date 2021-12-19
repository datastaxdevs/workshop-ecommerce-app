package com.datastax.tutorials.service.featured;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

/** Repository for {@link FeaturedEntity}. */
public interface FeaturedRepository extends CassandraRepository<FeaturedEntity, FeaturedPrimaryKey> {

    /**
     * Retrieve the list of categories of a parent partition.
     *
     * @param featureId
     *      featured
     * @return
     *      list of featured products
     */
    List<FeaturedEntity> findByKeyFeatureId(int featureId);
    
    /**
     * Find the category if exist.
     *
     * @param featureId
     *      featured
     * @param categoryId
     *      category
     * @return
     *      Specific featured product
     */
//    List<FeaturedEntity> findByKeyFeatureIdAndKeyCategoryId(int featureId, UUID categoryId);	
}
