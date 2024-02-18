package com.crebito.rinhabackend.repository;

import com.crebito.rinhabackend.entity.Cliente;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface ClienteRepository extends ReactiveCrudRepository<Cliente, Integer> {
    Mono<Cliente> buscarClientePorId(@Param("id") Integer id);
}
