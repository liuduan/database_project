package edu.tamu.ctv.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Rowtypes;

@Repository
public interface RowTypesRepository extends CrudRepository<Rowtypes, Long>
{
	List<Rowtypes> findByIdIn(Collection<Long> ids);
	
	List<Rowtypes> findByProjectsCode(String pCode);
	List<Rowtypes> findByProjectsId(Long id);
	List<Rowtypes> findByProjectsIdIn(Collection<Long> ids);
}
