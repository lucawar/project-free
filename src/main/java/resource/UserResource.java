package resource;

import dto.UserDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import mapper.UserMapper;
import model.User;
import service.UserService;

@Slf4j
@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @POST
    @Path("create-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createUser(UserDTO userDto) {

        try {
            log.info("UserDTO prima della copia: {}", userDto);
            User user = new User();
            userMapper.dtoToEntity(userDto, user);
            log.info("User dopo la copia: {}", user);
            userService.save(user);
            UserDTO responseDTO = new UserDTO();
            userMapper.entityToDto(user, responseDTO);
            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating user: " + e.getMessage()).build();
        }
    }
}
