package com.senai.Conta_Bancaria.application.dto;

import java.util.Set;

public record PagamentoResquestDTO (
   Long contaId,
   String boleto,
   Double valor,
   Set<Long> taxasIds;
){

}

