package com.crebito.rinhabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    private Integer id;

    private Integer saldo;

    private Integer limite;

}
