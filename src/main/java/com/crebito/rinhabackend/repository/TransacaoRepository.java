package com.crebito.rinhabackend.repository;

import com.crebito.rinhabackend.dto.TransacaoExtratoResponseDTO;
import com.crebito.rinhabackend.entity.Transacao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransacaoRepository extends ReactiveCrudRepository<Transacao, Integer> {
    Mono<Integer> getSaldoTotalByClienteId(Integer id);
    Flux<TransacaoExtratoResponseDTO> findByClienteIdOrderByRealizadaEm(Integer id);
}
