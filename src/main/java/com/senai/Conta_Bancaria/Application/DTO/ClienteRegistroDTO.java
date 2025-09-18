package com.senai.Conta_Bancaria.application.dto;

import com.senai.Conta_Bancaria.Domain.Entity.Cliente;
import java.util.ArrayList;

public record ClienteRegistroDTO(String Nome,
                                 String CPF,
                                 ContaResumoDTO contaDTO) {

    // Converte o DTO em uma entidade Cliente
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .Nome(this.Nome)   // Acessa diretamente os parâmetros do record
                .CPF(this.CPF)     // Acessa diretamente os parâmetros do record
                .contas(new ArrayList<>())  // Lista de contas, pode ser preenchida depois
                .build();
    }
}
