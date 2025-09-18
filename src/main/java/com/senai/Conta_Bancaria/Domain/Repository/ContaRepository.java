package com.senai.Conta_Bancaria.domain.repository;

import com.senai.Conta_Bancaria.Domain.Entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {
}
