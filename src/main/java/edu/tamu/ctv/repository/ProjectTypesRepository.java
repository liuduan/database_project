package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Projecttypes;

@Repository
public interface ProjectTypesRepository extends CrudRepository<Projecttypes, Long>
{
	List<Projecttypes> findByCode(String code);
}
