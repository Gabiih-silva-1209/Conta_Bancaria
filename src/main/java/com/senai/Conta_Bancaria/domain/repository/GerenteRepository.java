package com.senai.Conta_Bancaria.domain.repository;

import com.senai.Conta_Bancaria.domain.entity.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GerenteRepository extends JpaRepository<Gerente, String> {
    Optional<Gerente> findByEmail(String Email);

    List<Gerente> findAllByAtivoTrue();
}
//esqueci minha conta sou boba
