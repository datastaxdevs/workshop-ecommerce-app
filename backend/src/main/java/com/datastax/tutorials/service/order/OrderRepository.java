package com.datastax.tutorials.service.order;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface OrderRepository extends CassandraRepository<OrderEntity,OrderPrimaryKey> {

	Optional<OrderEntity> findByKeyOrderId(UUID orderId);
}
