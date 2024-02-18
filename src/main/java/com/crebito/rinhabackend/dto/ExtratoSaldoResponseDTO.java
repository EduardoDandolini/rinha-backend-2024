package com.crebito.rinhabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ExtratoSaldoResponseDTO(Integer total, @JsonProperty("data_extrato") LocalDateTime dataExtrato, Integer limite) {
}
