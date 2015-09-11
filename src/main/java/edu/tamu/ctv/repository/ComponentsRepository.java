package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Components;

@Component
public interface ComponentsRepository extends CrudRepository<Components, Long>
{
	List<Components> findByProjectsId(Long id);
}
