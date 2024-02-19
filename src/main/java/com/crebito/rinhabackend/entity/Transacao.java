package com.crebito.rinhabackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_transacao")
@SequenceGenerator(name = "seq_transacao", allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "VALOR", nullable = false)
    private Integer valor;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "REALIZADA_EM", nullable = false)
    private LocalDateTime realizadaEm;

}
