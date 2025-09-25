package com.senai.Conta_Bancaria.interface_ui.controller;

import com.senai.Conta_Bancaria.application.dto.ClienteAtualizadoDTO;
import com.senai.Conta_Bancaria.application.dto.ClienteRegistroDTO;
import com.senai.Conta_Bancaria.application.dto.ClienteResponseDTO;
import com.senai.Conta_Bancaria.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.data.projection.EntityProjection.ProjectionType.DTO;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor // criação de construtores

public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> registrarCliente(@RequestBody ClienteRegistroDTO dto) {
        ClienteResponseDTO novoCliente = service.registrarCliente(dto);

        return ResponseEntity.created(URI.create("/api/cliente/cpf/" + novoCliente.cpf())).body(novoCliente);
    }

    @GetMapping("/cpf{cpf}")
    public  ResponseEntity<ClienteResponseDTO> buscarClienteAtivoPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.buscarClienteAtivoPorCpf(cpf));

    }
        @PutMapping("/{cpf}")
        public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable String cpf,
                @RequestBody ClienteAtualizadoDTO dto) {
            ClienteResponseDTO clienteAtualizado = service.atualizarCliente(cpf, dto);
            return ResponseEntity.ok(service.atualizarCliente(cpf, dto));

        }
        @DeleteMapping("/cpf")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf){
        service.deletarCliente(cpf);
        return ResponseEntity.noContent().build();
        }
    }