package com.crebito.rinhabackend.controller;

import com.crebito.rinhabackend.dto.ExtratoResponseDTO;
import com.crebito.rinhabackend.dto.TransacaoRequestDTO;
import com.crebito.rinhabackend.dto.TransacaoResponseDTO;
import com.crebito.rinhabackend.service.CrebitoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientes")
public class CrebitoController {

    @Autowired
    private CrebitoService crebitoService;

    @PostMapping("/{id}/transacoes")
    public Mono<ResponseEntity<TransacaoResponseDTO>> efetuarTransacao(@PathVariable Integer id, @RequestBody @Valid TransacaoRequestDTO dto) {
        return crebitoService.efetuarTransacao(id, dto)
                .map(response -> ResponseEntity.ok(response))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/extrato")
    public Mono<ResponseEntity<ExtratoResponseDTO>> obterExtrato(@PathVariable Integer id) {
        return crebitoService.extrato(id)
                .map(extrato -> ResponseEntity.ok(extrato))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
