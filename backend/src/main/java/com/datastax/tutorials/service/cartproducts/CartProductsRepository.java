package com.datastax.tutorials.service.cartproducts;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface CartProductsRepository  extends CassandraRepository<CartProductEntity, CartProductsPrimaryKey> {

	List<CartProductEntity> findByKeyCartId(UUID cartId);
}
