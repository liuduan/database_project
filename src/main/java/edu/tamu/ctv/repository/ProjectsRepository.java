package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Projects;

@Repository
public interface ProjectsRepository extends CrudRepository<Projects, Long>
{
	Projects findTopByCode(String code);
	List<Projects> findByCreatedbyId(Long id);
}
