package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Columntypes;

@Repository
public interface ColumnTypesRepository extends CrudRepository<Columntypes, Long>
{
	List<Columntypes> findByProjectsCode(String pCode);
}
