package com.senai.Conta_Bancaria.Domain.Repository;

import com.senai.Conta_Bancaria.Domain.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
