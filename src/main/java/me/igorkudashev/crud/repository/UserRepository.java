package me.igorkudashev.crud.repository;

import me.igorkudashev.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author RulleR
 */
public interface UserRepository extends JpaRepository<User, Integer> {



}
