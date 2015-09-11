package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Results;

@Component
public interface ResultsRepository extends CrudRepository<Results, Long>
{
	List<Results> findByProjectsId(Long id);
}
