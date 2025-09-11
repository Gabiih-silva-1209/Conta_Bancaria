package Domain.Entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
public abstract class Conta {
    @NotNull(message = "Número de conta obrigatório" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 8, max = 12, message = "O número da conta deve ter entre 8 a 12 dígitos")
    private String númeroDaConta;


    private double saldo;

}
