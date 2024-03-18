package com.crebito.rinhabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    private Integer id;

    private String tipo;

    private Integer valor;

    private String descricao;

    private LocalDateTime realizadaEm;

    private Integer clienteId;

    public Transacao(String tipo, Integer valor, String descricao, LocalDateTime realizadaEm, Integer clienteId) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.realizadaEm = realizadaEm;
        this.clienteId = clienteId;
    }
}
