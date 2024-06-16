package mapper;

import dto.UserRequestDTO;
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
}
