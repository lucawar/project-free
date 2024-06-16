package service;

import dto.UserRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.UserMapper;
import model.User;
import repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @Transactional
    public UserRequestDTO createUser(UserRequestDTO userDto) {
        User user = new User();
        userMapper.dtoToEntity(user, userDto);
        userRepository.save(user);
        return userDto;
    }

    public UserRequestDTO getUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            UserRequestDTO responseDto = new UserRequestDTO();
            userMapper.entityToDto(responseDto, user);
            return responseDto;
        }
        return null;
    }

    public List<UserRequestDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserRequestDTO usersDTO = new UserRequestDTO();
                    userMapper.entityToDto(usersDTO, user);
                    return usersDTO;
                })
                .toList();
    }

    @Transactional
    public UserRequestDTO updateUser(Long id, UserRequestDTO userDto) {
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            userMapper.dtoToEntity(existingUser, userDto);
            userRepository.update(existingUser);
            UserRequestDTO responseDto = new UserRequestDTO();
            userMapper.entityToDto(responseDto, existingUser);
            return responseDto;
        }
        return null;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }
}
