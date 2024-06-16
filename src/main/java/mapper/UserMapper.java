package mapper;

import dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import model.User;

@ApplicationScoped
public class UserMapper extends AbstractMapper<User, UserDTO> {


    @Override
    public void dtoToEntity(UserDTO dto, User entity) {
        doCopy(dto, entity);
    }

    @Override
    public void entityToDto(User entity, UserDTO dto) {
        doCopy(entity, dto);
    }
}
