package com.crebito.rinhabackend.dto;

import com.crebito.rinhabackend.entity.Transacao;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record ExtratoResponseDTO(ExtratoSaldoResponseDTO saldo, @JsonProperty("ultimas_transacoes") List<TransacaoExtratoResponseDTO> extratoUltimasTransacoes) {
}
