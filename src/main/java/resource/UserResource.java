package resource;

import dto.request.UserRequestDTO;
import dto.response.UserResponseDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import mapper.UserMapper;
import service.UserService;

import java.util.List;

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
    public Response createUserRest(UserRequestDTO userDto) {
        try {
            UserRequestDTO requestDto = userService.createUser(userDto);
            log.info("[{}] Utente creato con successo",requestDto);
            return Response.status(Response.Status.CREATED).entity(requestDto).build();
        } catch (Exception e) {
            log.error("Error creating user", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating user: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("get-user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByIdRest(@PathParam("id") Long id) {
        try {
            UserResponseDTO userDto = userService.getUser(id);
            if (userDto != null) {
                return Response.ok(userDto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
            }
        } catch (Exception e) {
            log.error("Error retrieving  user", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retriveng User: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("get-all-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getusersRest() {
        try {
            List<UserResponseDTO> usersList = userService.getAllUsers();
            return Response.ok(usersList).build();
        } catch (Exception e) {
            log.error("Error retrieving  user", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retriveng Users: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("update-user{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateUserRest(@PathParam("id") Long id, UserRequestDTO userDto) {
        try {
            UserRequestDTO updateUserDto = userService.updateUser(id, userDto);
            if (updateUserDto != null) {
                return Response.ok(updateUserDto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
            }
        } catch (Exception e) {
            log.error("Error update user", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error update User: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("delete-user/{id}")
    @Transactional
    public Response deleteUserRest(@PathParam("id") Long id) {
        try {
            boolean deletedUser = userService.deleteUser(id);
            if (deletedUser) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            log.error("Error deleting user", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error delete User: " + e.getMessage()).build();
        }
    }
}
