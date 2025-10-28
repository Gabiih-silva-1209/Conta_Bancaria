package com.senai.Conta_Bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.senai.Conta_Bancaria.domain.enums.Role;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor


@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint( columnNames = "cpf") } )  //Construção de tabelas com alteração de novo
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;

    @NotBlank(message = "O nome do cliente não pode estar vazio")
    @Size(min=3, max = 120, message = "O nome deve ter entre 3 à 100 caracteres")
    @Column(nullable = false, length = 120)
    private String nome;

    @NotNull(message = "CPF obrigatório")
    @Size(max = 11, message = "O número do CPF deve conter 11 dígitos")
    @Column(nullable = false, length = 11)
    private String cpf;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected boolean ativo = true;

    @NotBlank
    @Column(nullable = false)
    protected String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;

}
