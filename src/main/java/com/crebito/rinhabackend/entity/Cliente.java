package com.crebito.rinhabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
@Table(name = "tb_cliente")
@SequenceGenerator(name = "seq_cliente", allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private CopyOnWriteArrayList<Transacao> transacoes = new CopyOnWriteArrayList<>();

    @Column(name = "SALDO", nullable = false)
    private Integer saldo;

    @Column(name = "LIMITE", nullable = false)
    private Integer limite;

}
