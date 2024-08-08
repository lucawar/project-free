package dto.request;

import enums.EuserGenderEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Data
@Slf4j
public class UserRequestDTO {

    private String nome;

    private String cognome;

    private String email;

    private LocalDate dataNascita;

    private EuserGenderEnum sesso;
}
