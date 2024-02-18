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

    //Tratar exceptions, criando exceptions personalizadas
    @Transactional
    public Mono<TransacaoResponseDTO> efetuarTransacao(Integer id, TransacaoRequestDTO dto) {
        return clienteRepository.buscarClientePorId(id)
                .flatMap(cliente -> {
                    if (dto.tipo().equals("c")) {
                        cliente.setSaldo(cliente.getSaldo() + dto.valor());
                    } else if (dto.tipo().equals("d")) {
                        if (cliente.getSaldo() - dto.valor() < -cliente.getLimite()) {
                            return Mono.error(new Exception("Saldo insuficiente para completar a transação"));
                        }
                        cliente.setSaldo(cliente.getSaldo() - dto.valor());
                    } else {
                        return Mono.error(new Exception("Tipo de transação inválido"));
                    }
                    return clienteRepository.save(cliente)
                            .map(savedCliente -> new TransacaoResponseDTO(savedCliente.getLimite(), savedCliente.getSaldo()));
                })
                .switchIfEmpty(Mono.error(new Exception("Cliente não encontrado")));
         }
    @Transactional
    public Mono<ExtratoResponseDTO> extrato(Integer id) {
        return clienteRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Cliente não encontrado")))
                .flatMap(cliente -> {
                    Mono<Integer> saldoTotal = transacaoRepository.getSaldoTotalByClienteId(id);
                    Flux<TransacaoExtratoResponseDTO> ultimasTransacoes = transacaoRepository.findByClienteIdOrderByRealizada(id);

                    return Mono.zip(saldoTotal, ultimasTransacoes.collectList())
                            .map(tuple -> {
                                Integer total = tuple.getT1();
                                List<TransacaoExtratoResponseDTO> transacoes = tuple.getT2();
                                ExtratoSaldoResponseDTO saldoDTO = new ExtratoSaldoResponseDTO(total, LocalDateTime.now(), cliente.getLimite());
                                return new ExtratoResponseDTO(saldoDTO, transacoes);
                            });
                });
            }
    }

