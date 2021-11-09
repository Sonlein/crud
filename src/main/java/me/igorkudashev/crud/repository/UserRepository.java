package me.igorkudashev.crud.repository;

import me.igorkudashev.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author RulleR
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
