package mapper;

import dto.request.UserRequestDTO;
import dto.response.UserResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import model.User;

@ApplicationScoped
public class UserMapper extends AbstractMapper<User, UserRequestDTO> {


    @Override
    public void dtoToEntity(User entity, UserRequestDTO userRequestDTO) {
        doCopy(entity, userRequestDTO);
    }

    @Override
    public void entityToDto(UserRequestDTO userRequestDTO, User entity) {
        doCopy(userRequestDTO, entity);
    }

    // Metodo specifico per UserResponseDTO per ricevere in output il DTO della response
    public void entityToResponseDto(UserResponseDTO dto, User entity) {
        doCopy(dto, entity);
    }

}
