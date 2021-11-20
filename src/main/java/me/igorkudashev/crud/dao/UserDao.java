package me.igorkudashev.crud.dao;

import me.igorkudashev.crud.model.User;

import java.util.List;

/**
 * @author RulleR
 */
public interface UserDao {

    void add(User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    User getByName(String name);
}
