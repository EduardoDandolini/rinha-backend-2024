package com.crebito.rinhabackend.service;

import com.crebito.rinhabackend.dto.*;
import com.crebito.rinhabackend.entity.Cliente;
import com.crebito.rinhabackend.entity.Transacao;
import com.crebito.rinhabackend.repository.ClienteRepository;
import com.crebito.rinhabackend.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CrebitoService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public Mono<TransacaoResponseDTO> efetuarTransacao(Integer id, TransacaoRequestDTO dto) {
        return clienteRepository.buscarClientePorId(id)
                .flatMap(cliente -> {
                    if (dto.tipo().equals("c")) {
                        cliente.setSaldo(cliente.getSaldo() + dto.valor());
                    } else if (dto.tipo().equals("d")) {
                        if (cliente.getSaldo() - dto.valor() < -cliente.getLimite()) {
                            return Mono.just(new TransacaoResponseDTO(cliente.getLimite(), cliente.getSaldo()));
                        }
                        cliente.setSaldo(cliente.getSaldo() - dto.valor());
                    } else {
                        return Mono.just(new TransacaoResponseDTO(cliente.getLimite(), cliente.getSaldo()));
                    }
                    cliente.efetuarTransacao(criarNovaTransacao(cliente, dto.tipo(), dto));
                    return clienteRepository.save(cliente)
                            .map(savedCliente -> new TransacaoResponseDTO(savedCliente.getLimite(), savedCliente.getSaldo()));
                });
        }

    @Transactional
    public Mono<ExtratoResponseDTO> extrato(Integer id) {
        return clienteRepository.buscarClientePorId(id)
                .flatMap(cliente -> {
                    Mono<Integer> saldoTotal = transacaoRepository.getSaldoTotalByClienteId(id)
                            .switchIfEmpty(Mono.just(0));
                    Flux<TransacaoExtratoResponseDTO> ultimasTransacoes = transacaoRepository.findByClienteIdOrderByRealizada(id)
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
        transacao.setCliente(cliente);
        return transacao;
    }
}

