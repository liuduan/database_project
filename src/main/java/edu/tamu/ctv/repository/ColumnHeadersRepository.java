package edu.tamu.ctv.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Columnheaders;

@Component
public interface ColumnHeadersRepository extends CrudRepository<Columnheaders, Long>
{
	@Query("select res from Columnheaders as res inner join res.columntypes ct inner join ct.projects p where res.code = :code and p.code = :projectCode")
	Columnheaders findByCodeAndHeaderTypesProjectsCode(String code, String projectCode);
}
