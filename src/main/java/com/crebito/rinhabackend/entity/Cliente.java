package com.crebito.rinhabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("cliente")
public class Cliente {
    @Id
    private Integer id;

    private Integer saldo;

    private Integer limite;

    public void efetuarTransacao(Transacao transacao){
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(transacao);
    }

}
