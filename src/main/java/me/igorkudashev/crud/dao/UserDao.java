package me.igorkudashev.crud.dao;

import me.igorkudashev.crud.model.User;

import java.util.List;

/**
 * @author RulleR
 */
public interface UserDao {

    void add(User user);

    User findById(int id);

    List<User> findAll();

    void deleteById(int id);

}
