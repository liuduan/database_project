package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Projectmembers;

@Component
public interface ProjectMembersRepository extends CrudRepository<Projectmembers, Long> {

}
