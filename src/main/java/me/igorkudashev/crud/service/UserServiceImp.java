package me.igorkudashev.crud.service;

import me.igorkudashev.crud.dao.UserDao;
import me.igorkudashev.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/**
 * @author RulleR
 */
@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImp(UserDao userDao, RoleService roleService, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public void add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public User getUserByAuth(Authentication authentication) {
        return authentication == null
                ? null
                : getByName(authentication.getName());
    }

    @PostConstruct
    public void fillDataBase() {
        findAll().forEach(user -> deleteById(user.getId()));
        add(new User("Igor", "pass",
                Set.of(roleService.findByName("ROLE_ADMIN"))));
        add(new User("Denis", "pass",
                Set.of(roleService.findByName("ROLE_USER"))));
    }
}
