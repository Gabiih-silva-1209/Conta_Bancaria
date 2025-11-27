package com.senai.Conta_Bancaria.interface_ui.controller;

import com.senai.Conta_Bancaria.application.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> pagar(@RequestBody PagamentoRequestDTO dto) {
        // aqui você converte dto em chamada service (carregar taxas pelos ids etc.)
        // exemplo simplificado:
        // Pagamento p = service.realizarPagamento(dto.getContaId(), dto.getBoleto(), dto.getValor(), taxasSet);
        // PagamentoResponseDTO resp = converter(p);
        return ResponseEntity.ok(/*resp*/ null);
    }
}
