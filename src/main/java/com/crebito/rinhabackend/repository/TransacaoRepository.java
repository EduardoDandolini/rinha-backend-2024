package com.crebito.rinhabackend.repository;

import com.crebito.rinhabackend.dto.TransacaoExtratoResponseDTO;
import com.crebito.rinhabackend.entity.Transacao;
import org.springframework.data.domain.Limit;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransacaoRepository extends R2dbcRepository<Transacao, Integer> {
    Mono<Integer> getSaldoTotalById(Integer id);
    @Query("SELECT * FROM transacao t WHERE t.cliente_id = :id ORDER BY t.realizada_em DESC LIMIT 10")
    Flux<TransacaoExtratoResponseDTO> findByIdOrderByRealizadaEmDesc(Integer id);
}
