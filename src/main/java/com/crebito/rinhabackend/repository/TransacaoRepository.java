package com.crebito.rinhabackend.repository;

import com.crebito.rinhabackend.dto.TransacaoExtratoResponseDTO;
import com.crebito.rinhabackend.entity.Transacao;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransacaoRepository extends ReactiveCrudRepository<Transacao, Integer> {
    Mono<Integer> getSaldoTotalByClienteId(Integer id);
    Flux<TransacaoExtratoResponseDTO> findByClienteIdOrderByRealizada(Integer id);
}
