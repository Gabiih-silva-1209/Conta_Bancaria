package com.senai.Conta_Bancaria.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteAtualizadoDTO(
    @Valid
        @NotBlank(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

    @NotBlank(message = "O CPF não pode estar vazio")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    String cpf
)

