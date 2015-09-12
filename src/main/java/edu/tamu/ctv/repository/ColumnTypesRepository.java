package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Columntypes;

@Component
public interface ColumnTypesRepository extends CrudRepository<Columntypes, Long>
{
	List<Columntypes> findByProjectsCode(String pCode);
}
