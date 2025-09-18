package com.senai.Conta_Bancaria.application.dto;

import java.math.BigDecimal;

public record ContaResumoDTO(String numeroDaConta,
                             String Tipo,
                             BigDecimal Saldo) {

public Conta toEntity (CLiente cliente){

    }

}
