package com.datastax.tutorials.service.user;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepository extends CassandraRepository<UserEntity, UUID> {

}
