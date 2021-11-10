package me.igorkudashev.crud.service;

import me.igorkudashev.crud.dao.UserDao;
import me.igorkudashev.crud.model.User;
import me.igorkudashev.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author RulleR
 */
@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @PostConstruct
    public void fillDataBase() {
        userDao.add(new User("Igor", "Kudashev", 187));
    }
}
