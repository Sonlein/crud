package me.igorkudashev.crud.dao;

import me.igorkudashev.crud.model.Role;

import java.util.List;

/**
 * @author RulleR
 */
public interface RoleDao {

    void add(Role role);

    List<Role> findAll();

    Role findById(Long id);

    Role findByName(String name);

    void deleteById(Long id);

}
