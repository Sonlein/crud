package me.igorkudashev.crud.dao;

import me.igorkudashev.crud.model.Role;

/**
 * @author RulleR
 */
public interface RoleDao {

    void add(Role role);

    Role findById(Long id);

    Role findByName(String name);

    void deleteById(Long id);

}
