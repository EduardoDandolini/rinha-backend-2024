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
@Table("transacao")
public class Transacao {
    @Id
    private Integer id;

    private String tipo;

    private Integer valor;

    private String descricao;

    private LocalDateTime realizadaEm;

}
