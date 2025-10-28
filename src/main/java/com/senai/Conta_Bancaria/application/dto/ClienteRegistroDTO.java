package com.senai.Conta_Bancaria.application.dto;

import com.senai.Conta_Bancaria.domain.entity.Cliente;
import com.senai.Conta_Bancaria.domain.entity.Conta;
import com.senai.Conta_Bancaria.domain.enums.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

public record ClienteRegistroDTO(
        @NotBlank(message = "O nome não pode estar vazio")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotBlank(message = "O cpf não pode estar vazio")
        @Size(min = 11, max = 11, message = "O cpf deve ter 11 caracteres")
        String cpf,

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        @NotBlank(message = "A senha não pode estar vazia")
        String senha,

        @NotBlank(message = "O email não pode estar vazio")
        @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
        String email,


        @Valid
        @NotNull(message = "O saldo não pode ser nulo")
        @Positive(message = "O saldo deve ser positivo")
        @NotBlank(message = "A senha não pode estar vazia")

        ContaResumoDTO contaDTO,
        Role role
        ) {

    // Converte o DTO em uma entidade Cliente
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)   // Acessa diretamente os parâmetros do record
                .cpf(this.cpf)     // Acessa diretamente os parâmetros do record
                .senha(this.senha) // Acessa diretamente os parâmetros do record
                .role(this.role)// Define a role do cliente
                .email(this.email) // Acessa diretamente os parâmetros do record
                .role(Role.CLIENTE)
                .contas(new ArrayList<>())  // Lista de contas, pode ser preenchida depois
                .build();
    }
}
