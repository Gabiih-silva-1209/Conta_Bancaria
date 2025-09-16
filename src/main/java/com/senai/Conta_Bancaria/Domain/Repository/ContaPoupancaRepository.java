package com.senai.Conta_Bancaria.Domain.Repository;

import com.senai.Conta_Bancaria.Domain.Entity.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaPoupancaRepository extends JpaRepository<ContaPoupanca, String> {
}
