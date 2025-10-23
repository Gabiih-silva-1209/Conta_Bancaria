package com.senai.Conta_Bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.experimental.SuperBuilder;


import java.util.List;


@Getter
@Setter//
@Entity
@NoArgsConstructor // Construtor vazio
@SuperBuilder // Consigo construir um objeto de acordo com paramêtros selecionados


public class Cliente extends Usuario {

//esqueci minha conta sou boba

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)  // Relação no banco de dados de um para muitos - 1 cliente N: contas (um cliente pode ter muitas contas)
    private List<Conta> contas; // criação da lista de conta
// cascade, gera a conta e referencia na tabela cliente


}
