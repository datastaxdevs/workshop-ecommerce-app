package com.datastax.tutorials.service.user;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserBySessionRepository extends CassandraRepository<UserBySessionEntity,String> {

}
