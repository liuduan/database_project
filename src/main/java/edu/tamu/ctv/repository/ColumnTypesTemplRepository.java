package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Columntypestempl;

@Repository
public interface ColumnTypesTemplRepository extends CrudRepository<Columntypestempl, Long>
{
}
