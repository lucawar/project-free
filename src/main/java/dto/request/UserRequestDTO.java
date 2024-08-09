package dto.request;

import enums.EuserGenderEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserRequestDTO {

    @NotNull(message = "Il campo nome è obbligatorio")
    @Size(max = 255, message = "Il nome non può contenere più di 255 caratteri")
    private String nome;

    @NotNull(message = "Il campo cognome è obbligatorio")
    @Size(max = 255, message = "Il cognome non può contenere più di 255 caratteri")
    private String cognome;

    @NotNull(message = "Il campo dataNascita è obbligatorio")
    private LocalDate dataNascita;

    @NotNull(message = "Il campo nome è obbligatorio")
    @Email(message = "L'email non è valida: deve contenere '@' e un dominio")
    private String email;

    @NotNull(message = "Il campo sesso è obbligatorio")
    private EuserGenderEnum sesso;
}
