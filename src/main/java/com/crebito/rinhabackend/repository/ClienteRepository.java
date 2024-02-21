package com.crebito.rinhabackend.repository;

import com.crebito.rinhabackend.entity.Cliente;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface ClienteRepository extends ReactiveCrudRepository<Cliente, Integer> {
    @Query("SELECT * FROM cliente WHERE id = :id")
    Mono<Cliente> buscarClientePorId(@Param("id") Integer id);
}
