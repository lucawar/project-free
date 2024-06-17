package dto.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class UserRequestDTO {

    private String nome;

    private String cognome;

    private String email;
}
