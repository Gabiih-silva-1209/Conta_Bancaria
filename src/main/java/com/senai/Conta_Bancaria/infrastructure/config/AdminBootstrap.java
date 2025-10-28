package com.senai.Conta_Bancaria.infrastructure.config;

import com.senai.Conta_Bancaria.domain.entity.Gerente;
import com.senai.Conta_Bancaria.domain.repository.GerenteRepository;
import com.senai.Conta_Bancaria.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AdminBootstrap implements CommandLineRunner {

    private final GerenteRepository gerenteRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${sistema.admin.email}")
    private String adminEmail;

    @Value("${sistema.admin.senha}")
    private String adminSenha;

    @Override
    public void run(String... args) {
        gerenteRepository.findByEmail(adminEmail).ifPresentOrElse(
                gerente -> {
                    if (!gerente.isAtivo()) {
                        gerente.setAtivo(true);
                        gerenteRepository.save(gerente);
                    }
                },
                () -> {
                    Gerente admin = Gerente.builder()
                            .nome("Cadastro Provisório")
                            .email(adminEmail)
                            .cpf("00000000000")
                            .senha(passwordEncoder.encode(adminSenha))
                            .role(Role.ADMIN)
                            .ativo(true)
                            .build();
                    gerenteRepository.save(admin);
                    System.out.println("⚡ Usuário admin provisório criado: " + adminEmail);
                }
        );
    }
}