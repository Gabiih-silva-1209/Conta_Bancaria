package com.senai.Conta_Bancaria.application.dto;

import com.senai.Conta_Bancaria.domain.entity.Cliente;
import com.senai.Conta_Bancaria.domain.entity.Conta;

import java.util.ArrayList;

public record ClienteRegistroDTO(String nome,
                                 String cpf,
                                 ContaResumoDTO contaDTO) {

    // Converte o DTO em uma entidade Cliente
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)   // Acessa diretamente os parâmetros do record
                .cpf(this.cpf)     // Acessa diretamente os parâmetros do record
                .contas(new ArrayList<>())  // Lista de contas, pode ser preenchida depois
                .build();
    }
}
