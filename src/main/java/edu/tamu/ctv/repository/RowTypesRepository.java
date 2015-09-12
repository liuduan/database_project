package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Rowtypes;

@Component
public interface RowTypesRepository extends CrudRepository<Rowtypes, Long>
{
	List<Rowtypes> findByProjectsCode(String pCode);
}
