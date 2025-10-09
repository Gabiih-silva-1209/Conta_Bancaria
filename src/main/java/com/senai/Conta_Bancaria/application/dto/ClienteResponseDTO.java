package com.senai.Conta_Bancaria.application.dto;

import com.senai.Conta_Bancaria.domain.entity.Cliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClienteResponseDTO(
        @NotNull(message = "O id não pode ser nulo")
        String id,
        @NotBlank(message = "O nome não pode estar vazio")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotBlank(message = "O cpf não pode estar vazio")
        @Size(min = 11, max = 11, message = "O cpf deve ter 11 caracteres")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve ter o formato XXX.XXX.XXX-XX")
        String cpf,
        @Valid
        @NotNull(message = "A lista de contas não pode ser nula")
        List<ContaResumoDTO> contas
){
        public static ClienteResponseDTO fromEntity(Cliente cliente) {
                List<ContaResumoDTO> contas = cliente.getContas().stream().map(ContaResumoDTO::fromEntity).toList();
                return new ClienteResponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCpf(),
                        contas
                );

        }
}
