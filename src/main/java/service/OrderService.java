package service;

import dto.OrderDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Order;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    EntityManager em;

    @Transactional
    public OrderDTO create(OrderDTO orderDTO) throws InvocationTargetException, IllegalAccessException {
        Order order = new Order();
        em.persist(order);
        BeanUtils.copyProperties(order, orderDTO);
        return orderDTO;
    }

    public OrderDTO getId(Long id) throws InvocationTargetException, IllegalAccessException {
        Order order = em.find(Order.class, id);
        OrderDTO responseDto = new OrderDTO();
        //userMapper.entityToDto(responseDto, user);
        BeanUtils.copyProperties(responseDto, order);
        return responseDto;
    }

    public List<Order> getByIdUser(Long userId) {
        return em.createQuery("SELECT o FROM Order o WHERE o.userId = :userId",
                        Order.class).setParameter("userId", userId)
                .getResultList();
    }
}
