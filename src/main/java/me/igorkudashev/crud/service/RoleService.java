package me.igorkudashev.crud.service;

import me.igorkudashev.crud.model.Role;

/**
 * @author RulleR
 */
public interface RoleService {

    Role findById(Long id);

    Role findByName(String name);

    void deleteById(Long id);
}
