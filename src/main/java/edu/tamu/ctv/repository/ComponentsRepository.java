package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Components;
import edu.tamu.ctv.entity.Results;
import edu.tamu.ctv.entity.Rowtypes;

@Repository("componentsRepository")
public interface ComponentsRepository extends CrudRepository<Components, Long>
{
	
	@Query("select res"+
	"from Components as res"+
	"inner join res.projects p"+
	"where p.id = :id")
		List<Components> findByProjectsId(@Param("id")Long id);
	
	Components findByCodeAndProjectsCode(String code, String pCode);
}
