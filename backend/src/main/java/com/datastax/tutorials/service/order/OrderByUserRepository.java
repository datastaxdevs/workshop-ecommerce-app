package com.datastax.tutorials.service.order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface OrderByUserRepository  extends CassandraRepository<OrderByUserEntity,OrderByUserPrimaryKey>{
	
	Optional<List<OrderByUserEntity>> findByKeyUserId(UUID userId);
}
