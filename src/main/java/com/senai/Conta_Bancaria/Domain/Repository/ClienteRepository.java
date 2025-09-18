package com.senai.Conta_Bancaria.domain.repository;

import com.senai.Conta_Bancaria.Domain.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByCPFandAtivoTrue(String CPF);
}
