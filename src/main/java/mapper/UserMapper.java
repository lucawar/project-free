package mapper;

import dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import model.User;

@ApplicationScoped
public class UserMapper extends AbstractMapper<User, UserDTO> {


    @Override
    public User dtoToEntity(UserDTO dto, User entity) {
        doCopy(dto, entity);
        return entity;
    }

    @Override
    public UserDTO entityToDto(User entity, UserDTO dto) {
        doCopy(entity, dto);
        return dto;
    }
}
