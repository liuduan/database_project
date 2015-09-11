package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Users;

@Component
public interface UsersRepository extends CrudRepository<Users, Long> {
    List<Users> findByLogin(String login);
}
