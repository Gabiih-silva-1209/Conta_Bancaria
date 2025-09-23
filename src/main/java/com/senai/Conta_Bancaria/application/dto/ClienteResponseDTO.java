package com.senai.Conta_Bancaria.application.dto;

import com.senai.Conta_Bancaria.domain.entity.Cliente;

import java.util.List;

public record ClienteResponseDTO(
        String id,
        String Nome,
        String CPF,
        List<ContaResumoDTO> contas
){
        public static ClienteResponseDTO fromEntity(Cliente cliente) {

        }
}
