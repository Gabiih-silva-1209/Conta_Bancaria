package com.senai.Conta_Bancaria.interface_ui.controller;

import com.senai.Conta_Bancaria.application.dto.ClienteRegistroDTO;
import com.senai.Conta_Bancaria.application.dto.ClienteResponseDTO;
import com.senai.Conta_Bancaria.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.created(URI.create("/api/cliente/cpf/" + novoCliente.CPF())).body(novoCliente);
    }
}