package me.igorkudashev.crud.service;

import me.igorkudashev.crud.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @author RulleR
 */
public interface UserService {

    void add(User user);

    void update(User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    User getByName(String name);

    User getUserByAuth(Authentication authentication);

}
