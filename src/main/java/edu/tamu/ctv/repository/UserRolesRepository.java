package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Userroles;

@Repository
public interface UserRolesRepository extends CrudRepository<Userroles, Long> {

}
