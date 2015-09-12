package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Rowheaders;

@Component
public interface RowHeadersRepository extends CrudRepository<Rowheaders, Long>
{
	@Query("select res from Rowheaders as res inner join res.rowtypes rt inner join rt.projects p where res.code = :code and p.code = :projectCode")
	Rowheaders findByCodeAndRowTypesProjectsCode(String code, String projectCode);
}
