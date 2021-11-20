package me.igorkudashev.crud.dao;

import me.igorkudashev.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.Set;

/**
 * @author RulleR
 */
@Transactional
@Repository
@DependsOn(value = "RoleDao")
public class UserDaoImp implements UserDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private final RoleDao roleDao;

    @Autowired
    public UserDaoImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select a from User a", User.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public User getByName(String name) {
        return entityManager.createQuery("select a from User a where a.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
