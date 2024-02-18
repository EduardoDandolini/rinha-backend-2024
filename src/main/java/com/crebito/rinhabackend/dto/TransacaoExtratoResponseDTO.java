package com.crebito.rinhabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record TransacaoExtratoResponseDTO(Integer valor, char tipo, String descricao, @JsonProperty("realizada_em") LocalDateTime realizadaEm) {
}
