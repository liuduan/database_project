package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Components;
import edu.tamu.ctv.entity.Rowtypes;

@Repository("componentsRepository")
public interface ComponentsRepository extends CrudRepository<Components, Long>
{
	List<Components> findByProjectsId(Long id);

	Components findByCodeAndProjectsCode(String code, String pCode);
}
