package com.datastax.tutorials.service.order;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface OrderStatusHistoryRepository extends CassandraRepository<OrderStatusHistoryEntity,OrderStatusHistoryPrimaryKey> {

	Optional<OrderStatusHistoryEntity> findByKeyOrderId(UUID orderId);
}
