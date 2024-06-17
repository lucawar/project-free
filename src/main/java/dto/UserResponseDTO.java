package dto;

import enums.UserGenderEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {

    private UUID userUuid;

    private String nome;

    private String cognome;

    private String email;

    private UserGenderEnum sesso;
}
