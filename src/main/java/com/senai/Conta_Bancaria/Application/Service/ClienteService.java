package com.senai.Conta_Bancaria.application.service;

import.com.senai.Conta_Bancaria.application.dto.ClienteRegistroDTO;
import com.senai.Conta_Bancaria.application.dto.ClienteResponseDTO;
import com.senai.Conta_Bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;


    public ClienteResponseDTO registrarCliente(ClienteRegistroDTO dto){

        var cliente = repository.findByCPFandAtivoTrue(dto.cpf().orElseGet(
                () -> repository.save(dto.toEntity)));


        var contas = cliente.getContas();
        var novaConta = dto.contaDTO().toEntity(cliente);
        var contas = cliente.getContas();
        boolean jaTemTipo = contas.stream()
                .anyMatch(Conta c -> c.getClass().equals(dto.conta().getClass()) && c.isAtiva);
return
    }
}
