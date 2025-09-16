package Domain.Entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance (strategy = InheritanceType.SINGLE_TABLE) // criação de tabelas
@DiscriminatorColumn (name = "tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 20)
@Table(name = "conta", uniqueConstraints = {
        @UniqueConstraint(name = "uk_conta_numero", columnNames = "numero"),
        @UniqueConstraint(name = "uk_cliente_tipo", columnNames = {"cliente_id", "tipo_conta"})
})

public abstract class Conta {
    @NotNull(message = "Número de conta obrigatório" )
 @GeneratedValue (strategy = GenerationType.UUID)
    @Size(min = 8, max = 12, message = "O número da conta deve ter entre 8 a 12 dígitos")
    private String numeroDaConta;


    private BigDecimal saldo;

    private String id;


}
