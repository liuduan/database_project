package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Rowtypestempl;

@Repository
public interface RowTypesTemplRepository extends CrudRepository<Rowtypestempl, Long>
{
	List<Rowtypestempl> findByProjecttypeId(Long id);
}
