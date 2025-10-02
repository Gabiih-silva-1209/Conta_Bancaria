package com.senai.Conta_Bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.math.BigDecimal;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // criação de tabelas
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 20)
@Table(name = "conta", uniqueConstraints = {
        @UniqueConstraint(name = "uk_conta_numero", columnNames = "numero_da_conta"),
        @UniqueConstraint(name = "uk_cliente_tipo", columnNames = {"cliente_id", "tipo_conta"})
})
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull(message = "Número de conta obrigatório")
    @Size(min = 8, max = 12, message = "O número da conta deve ter entre 8 a 12 dígitos")
    @Column(nullable = false, length = 20)
    private String numero;

    @Column(nullable = false, precision = 4)
    private BigDecimal saldo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_conta_cliente"))
    private Cliente cliente;

    @Column(nullable = false)
    private boolean ativa;

    public abstract String getTipo();

    public void sacar(BigDecimal valor) {
        validarValorMaiorQueZero(valor);
        if (valor.compareTo(saldo)>0){
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }
            saldo = saldo.subtract(valor);
    }

    protected static void validarValorMaiorQueZero(BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("O valor de saque deve ser positivo.");
        }
    }

    public void depositar(BigDecimal valor) {
        validarValorMaiorQueZero(valor);
        saldo = saldo.add(valor);

    }
    public void transferir(BigDecimal valor, Conta contaDestino){
        if (this.id.equals(contaDestino.getId())){
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta.");
        }
        this.sacar(valor);
        contaDestino.depositar(valor);
    }
}
