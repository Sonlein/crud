package me.igorkudashev.crud.dao;

import me.igorkudashev.crud.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author RulleR
 */
@Transactional
@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findByName(String name) {
        return entityManager.createQuery("select a from Role a where a.role=:name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @PostConstruct
    public void fillDataBase() {
        add(new Role("ROLE_ADMIN"));
        add(new Role("ROLE_USER"));
    }
}
