package com.senai.Conta_Bancaria.domain.repository;

import com.senai.Conta_Bancaria.domain.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByBoleto(String boleto);
}
