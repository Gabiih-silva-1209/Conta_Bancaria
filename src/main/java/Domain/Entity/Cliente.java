package Domain.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import javax.persistence.Entity;


@Data
@Entity
public class Cliente {
    @NotBlank(message = "O nome do cliente não pode estar vazio")
    @Size(min=3, max = 100, message = "O nome deve ter entre 3 à 100 caracteres")
    private String Nome;

    @NotNull(message = "CPF obrigatório")
    @Size(max = 11, message = "O númer do CPF deve conter 11 dígitos")
    private Long CPF;
}
