package com.senai.Conta_Bancaria.application.service;

import com.senai.Conta_Bancaria.application.dto.ClienteRegistroDTO;
import com.senai.Conta_Bancaria.application.dto.ClienteResponseDTO;
import com.senai.Conta_Bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;


    public ClienteResponseDTO registrarCliente(ClienteRegistroDTO dto){

        var cliente = repository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
                () -> repository.save(dto.toEntity())
        );
        var contas = cliente.getContas();
        var novaConta = dto.contaDTO().toEntity(cliente);

        boolean jaTemTipo = contas.stream().anyMatch(
                c -> c.getClass().equals(novaConta.getClass()) && c.isAtiva()
                );
                if (jaTemTipo)
                    throw  new RuntimeException("Cliente já possui uma conta desse tipo");
                cliente.getContas().add(novaConta);

                return ClienteResponseDTO.fromEntity(repository.save(cliente));

                public List<ClienteResponseDTO>listarClientesAtivos(){
                    return repository.findByCpfAndAtivoTrue().stream()
                            .map(ClienteResponseDTO::fromEntity)
                            .toList();
        }
    }
}
