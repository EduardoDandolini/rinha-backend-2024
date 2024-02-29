package com.crebito.rinhabackend.service;

import com.crebito.rinhabackend.dto.*;
import com.crebito.rinhabackend.entity.Cliente;
import com.crebito.rinhabackend.entity.Transacao;
import com.crebito.rinhabackend.repository.ClienteRepository;
import com.crebito.rinhabackend.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CrebitoService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    public Mono<TransacaoResponseDTO> efetuarTransacao(Integer id, TransacaoRequestDTO dto) {
        return clienteRepository.buscarClientePorId(id)
                .flatMap(cliente -> {
                    calcularSaldo(cliente, dto);
                    cliente.efetuarTransacao(criarNovaTransacao(cliente, dto.tipo(), dto));
                    return clienteRepository.save(cliente)
                            .map(savedCliente -> new TransacaoResponseDTO(savedCliente.getLimite(), savedCliente.getSaldo()));
                });
    }
    public Mono<ExtratoResponseDTO> extrato(Integer id) {
        return clienteRepository.buscarClientePorId(id)
                .flatMap(cliente -> {
                    Mono<Integer> saldoTotal = transacaoRepository.getSaldoTotalById(id)
                            .switchIfEmpty(Mono.just(0));
                    Flux<TransacaoExtratoResponseDTO> ultimasTransacoes = transacaoRepository.findByIdOrderByRealizadaEm(id)
                            .switchIfEmpty(Flux.empty());

                    return Mono.zip(saldoTotal, ultimasTransacoes.collectList())
                            .map(tuple -> {
                                Integer total = tuple.getT1();
                                List<TransacaoExtratoResponseDTO> transacoes = tuple.getT2();
                                ExtratoSaldoResponseDTO saldoDTO = new ExtratoSaldoResponseDTO(total, LocalDateTime.now(), cliente.getLimite());
                                return new ExtratoResponseDTO(saldoDTO, transacoes);
                            });
                });
    }

    private Transacao criarNovaTransacao(Cliente cliente, String tipo, TransacaoRequestDTO requestDTO) {
        Transacao transacao = new Transacao();
        transacao.setTipo(tipo);
        transacao.setRealizadaEm(LocalDateTime.now());
        transacao.setValor(requestDTO.valor());
        cliente.setId(cliente.getId());
        return transacao;
    }

    private void calcularSaldo(Cliente cliente, TransacaoRequestDTO dto) {
        if (dto.tipo().equals("c")) {
            cliente.setSaldo(cliente.getSaldo() + dto.valor());
        } else if (dto.tipo().equals("d")) {
            if (cliente.getSaldo() - dto.valor() < -cliente.getLimite()) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            cliente.setSaldo(cliente.getSaldo() - dto.valor());
        }
    }
}

