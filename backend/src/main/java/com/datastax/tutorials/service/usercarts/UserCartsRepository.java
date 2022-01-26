package com.datastax.tutorials.service.usercarts;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserCartsRepository extends CassandraRepository<UserCartEntity, UserCartsPrimaryKey> {

	List<UserCartEntity> findByKeyUserId(UUID userId);	
}
