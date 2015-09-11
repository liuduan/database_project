package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Userroles;

@Component
public interface UserRolesRepository extends CrudRepository<Userroles, Long> {

}
