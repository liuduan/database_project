package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Projects;

@Component
public interface ProjectsRepository extends CrudRepository<Projects, Long> {

}
