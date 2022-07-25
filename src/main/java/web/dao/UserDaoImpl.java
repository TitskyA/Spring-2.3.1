package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
        List<User> users = entityManager.createQuery("SELECT u FROM User u ", User.class).getResultList();
        return users;
    }

    @Override
    public User get(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void update(Long id, User user) {
        User updatedUser = entityManager.merge(get(id));
        updatedUser.setName(user.getName());
        updatedUser.setLastname(user.getLastname());
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(get(id));
    }
}
