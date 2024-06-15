package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.User;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager em;

    public void save(User user){
        em.persist(user);
    }
}
