package com.datastax.tutorials.service.price;

import java.util.List;
import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * Cassandra accessed to table Price with spring data.
 *
 * @author Cedrick LUNVEN
 * @author Aaron PLOETZ
 */
@Repository
public interface PriceRepository extends CassandraRepository<PriceEntity, PricePrimaryKey> {
    
    /**
     * Retrieve the list of prices of the productId partition.
     *
     * @param productId
     *      productId
     * @return
     *      list of prices
     */
    List<PriceEntity> findByKeyProductId(String productId);
    
    /**
     * Find the price for a store if exist.
     * @param productId
     *      productId
     * @param storeId
     *      storeId
     * @return
     *      price if it exists in the db
     */
    Optional<PriceEntity> findByKeyProductIdAndKeyStoreId(String productId, String storeId);
    
    /**
     * Delete price if exist.
     *
     * @param productId
     *      current product
     */
    void deleteByKeyProductId(String productId);

    /**
     * Delete price for a store.
     *
     * @param productId
     *      product identifier
     * @param storeId
     *      store identifier
     */
    void deleteByKeyProductIdAndKeyStoreId(String productId, String storeId);

}
