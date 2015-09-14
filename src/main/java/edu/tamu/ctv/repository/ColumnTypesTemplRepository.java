package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Columntypestempl;

@Repository
public interface ColumnTypesTemplRepository extends CrudRepository<Columntypestempl, Long>
{
	List<Columntypestempl> findByProjecttypeId(Long id);
}
