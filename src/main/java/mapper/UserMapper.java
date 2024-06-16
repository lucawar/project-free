package mapper;

import dto.UserRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import model.User;

@ApplicationScoped
public class UserMapper extends AbstractMapper<User, UserRequestDTO> {


    @Override
    public void dtoToEntity(UserRequestDTO dto, User entity) {
        doCopy(dto, entity);
    }

    @Override
    public void entityToDto(User entity, UserRequestDTO dto) {
        doCopy(entity, dto);
    }
}
