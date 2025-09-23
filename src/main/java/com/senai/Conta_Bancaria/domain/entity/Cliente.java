package com.senai.Conta_Bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;



import java.util.List;


@Data //
@Entity
@AllArgsConstructor // Todos os construtores
@NoArgsConstructor // Construtor vazio
@Builder // Consigo construir um objeto de acordo com paramêtros selecionados
@Table(name = "cliente",
        uniqueConstraints = {
        @UniqueConstraint( columnNames = "CPF") } )  //Construção de tabelas com alteração de novo

public class Cliente {
    @NotBlank(message = "O nome do cliente não pode estar vazio")
    @Size(min=3, max = 120, message = "O nome deve ter entre 3 à 100 caracteres")
    @Column(nullable = false, length = 120)
    private String Nome;

    @NotNull(message = "CPF obrigatório")
    @Size(max = 11, message = "O número do CPF deve conter 11 dígitos")
    @Column(nullable = false, length = 11)
    private String CPF;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)  // Relação no banco de dados de um para muitos - 1 cliente N: contas (um cliente pode ter muitas contas)
    private List<Conta> contas; // criação da lista de conta
// cascade, gera a conta e referencia na tabela cliente

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private Boolean ativo;
}
