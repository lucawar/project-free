package dto.response;

import enums.EuserGenderEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserResponseDTO {

    private UUID userUuid;

    private String nome;

    private String cognome;

    private String email;

    private LocalDate dataNascita;

    private EuserGenderEnum sesso;

}
