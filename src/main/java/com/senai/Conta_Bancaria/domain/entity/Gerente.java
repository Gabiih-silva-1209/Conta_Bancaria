package com.senai.Conta_Bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "gerentes")
public class Gerente extends Usuario {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "gerente_agencias", joinColumns = @JoinColumn(name = "gerente_id"))
    @Column(name = "agencia")
    private List<String> agenciasResponsaveis;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "gerente_departamentos", joinColumns = @JoinColumn(name = "gerente_id"))
    @Column(name = "departamento")
    private List<String> departamentos;

}
