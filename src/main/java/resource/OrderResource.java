package resource;

import dto.OrderDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import model.Order;
import service.OrderService;

import java.util.List;

@ApplicationScoped
@Slf4j
@Path("order/")
public class OrderResource {

    @Inject
    OrderService orderService;

    @POST
    @Path("create-order")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createOrderRest(OrderDTO orderDTO) {
        try {
            OrderDTO requestDto = orderService.create(orderDTO);
            return Response.status(Response.Status.CREATED).entity(requestDto).build();
        } catch (Exception e) {
            log.error("Error creating user", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating order: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("get-user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByIdRest(@PathParam("id") Long id) {
        try {
            OrderDTO userDto = orderService.getId(id);
            if (userDto != null) {
                return Response.ok(userDto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Order not found").build();
            }
        } catch (Exception e) {
            log.error("Error retrieving  Order", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retriveng Order: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDTO> getOrdersByUserId(@PathParam("userId") Long userId) {
        List<Order> orders = orderService.getByIdUser(userId);
        return orders.stream().map(
                order -> new OrderDTO(order.getUserId(), order.getProduct(), order.getQuantity(), order.getId());
        )
    }
}
