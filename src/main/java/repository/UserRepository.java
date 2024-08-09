package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.User;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository {

    @Inject
    EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u From User u", User.class).getResultList();
    }

    public User update(User user) {
        return em.merge(user);
    }

    public void delete(User user) {
        if (em.contains(user)) {
            em.remove(user);
        } else {
            em.remove(em.merge(user));
        }
    }

    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst();
    }

    public void deleteById(Long id) {
        User user = findById(id);
        if (user != null) {
            delete(user);
        }
    }
}
