package com.senai.Conta_Bancaria.domain.repository;

import com.senai.Conta_Bancaria.Domain.Entity.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, String> {
}
