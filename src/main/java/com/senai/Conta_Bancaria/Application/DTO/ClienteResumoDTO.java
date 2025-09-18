package com.senai.Conta_Bancaria.application.dto;

import java.util.List;

public record ClienteResumoDTO(
        String id,
        String nome,
        String cpf

        List<ContaResumoDTO>contas;
) {
}
