package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Columnheaders;
import edu.tamu.ctv.entity.Results;

@Repository
public interface ResultsRepository extends CrudRepository<Results, Long>
{
	//@Query("from Results as res inner join res.projects project where project.id = :id")
	List<Results> findByProjectsId(@Param("id")Long id);
}
