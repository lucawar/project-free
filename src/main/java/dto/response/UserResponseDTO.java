package dto.response;

import enums.EuserGenderEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserResponseDTO {

    private UUID userUuid;

    private String nome;

    private String cognome;

    private String email;

    private LocalDate dataNascita;

    private EuserGenderEnum sesso;

}
