package com.crebito.rinhabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Integer id;

    private CopyOnWriteArrayList<Transacao> transacoes = new CopyOnWriteArrayList<>();

    private Integer saldo;

    private Integer limite;

    public void efetuarTransacao(Transacao transacao){
        this.transacoes.add(transacao);
    }

}
