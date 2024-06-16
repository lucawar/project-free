package service;

import dto.UserDTO;
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
    public UserDTO createUser(UserDTO userDto) {
        User user = new User();
        userMapper.dtoToEntity(userDto, user);
        userRepository.save(user);
        return userDto;
    }

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            UserDTO responseDto = new UserDTO();
            userMapper.entityToDto(user, responseDto);
            return responseDto;
        }
        return null;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserDTO usersDTO = new UserDTO();
                    userMapper.entityToDto(user, usersDTO);
                    return usersDTO;
                })
                .toList();
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDto) {
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            userMapper.dtoToEntity(userDto, existingUser);
            userRepository.update(existingUser);
            UserDTO responseDto = new UserDTO();
            userMapper.entityToDto(existingUser, responseDto);
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
