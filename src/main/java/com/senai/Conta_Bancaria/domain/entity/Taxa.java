package com.senai.Conta_Bancaria.domain.entity;

import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Taxa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String descricao;

    @Column(precision = 10, scale = 2)
    private BigDecimal percentual;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorFixo;
}
