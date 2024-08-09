package service;

import dto.request.UserRequestDTO;
import dto.response.UserResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public UserRequestDTO createUser(@Valid UserRequestDTO userDto) {
        User user = new User();
        userMapper.dtoToEntity(user, userDto);
        userRepository.save(user);
        return userDto;
    }

    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userMapper.entityToResponseDto(userResponseDTO, user);
            return userResponseDTO;
        }
        return null;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserResponseDTO userResponseDTO = new UserResponseDTO();
                    userMapper.entityToResponseDto(userResponseDTO, user);
                    return userResponseDTO;
                })
                .toList();
    }

    @Transactional
    public UserRequestDTO updateUser(Long id, UserRequestDTO userDto) {
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            userMapper.dtoToEntity(existingUser, userDto);
            userRepository.update(existingUser);
            UserRequestDTO userRequestDTO = new UserRequestDTO();
            userMapper.entityToDto(userRequestDTO, existingUser);
            return userRequestDTO;
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

    public boolean emailUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
