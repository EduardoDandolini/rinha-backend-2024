package com.crebito.rinhabackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public record TransacaoRequestDTO(@NotNull Integer valor,
                                  @NotEmpty @Pattern(regexp = "^(c|d)$" ) String tipo,
                                  @NotEmpty @NotBlank @Size(min = 1, max = 10) String descricao) {

}
