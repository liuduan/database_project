package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Projecttypes;

@Component
public interface ProjectTypesRepository extends CrudRepository<Projecttypes, Long> {

}
