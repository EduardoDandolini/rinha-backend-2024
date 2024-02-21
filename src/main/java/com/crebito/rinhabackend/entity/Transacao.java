package com.crebito.rinhabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    private Integer id;

    private Cliente cliente;

    private String tipo;

    private Integer valor;

    private String descricao;

    private LocalDateTime realizadaEm;

}
