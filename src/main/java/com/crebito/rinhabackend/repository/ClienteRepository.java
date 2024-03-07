package com.crebito.rinhabackend.repository;

import com.crebito.rinhabackend.entity.Cliente;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface ClienteRepository extends R2dbcRepository<Cliente, Integer> {
    @Query("SELECT * FROM cliente c WHERE c.id = :id")
    Mono<Cliente> buscarClientePorId(@Param("id") Integer id);
}
