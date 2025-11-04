package com.senai.Conta_Bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Pagamento {

@Id
@GeneratedValue(strategy = GenerationType.UUID)
private String id;

@ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

private String boleto;

@Column(precision = 19, scale = 2)
private BigDecimal valorPago;

private LocalDateTime dataPagamento;

private String status;

@ManyToMany
    @JoinTable(
            name = "pagamento_taxa",
            joinColumns = @JoinColumn(name = "pagamento_id"),
            inverseJoinColumns = @JoinColumn(name = "taxa_id")
    )
    private Set<Taxa> taxa;
}
