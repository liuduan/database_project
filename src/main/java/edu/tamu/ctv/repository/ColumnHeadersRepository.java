package edu.tamu.ctv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Columnheaders;

@Repository
public interface ColumnHeadersRepository extends CrudRepository<Columnheaders, Long>
{
	@Query("select res from Columnheaders as res inner join res.columntypes ct inner join ct.projects p where res.code = :code and p.code = :projectCode")
	List<Columnheaders> findByCodeAndHeaderTypesProjectsCode(@Param("code")String code, @Param("projectCode")String projectCode);
	
	@Query("select res from Columnheaders as res inner join res.columntypes ct inner join ct.projects p where p.id = :id")
	List<Columnheaders> findByHeaderTypesProjectsId(@Param("id")Long id);
	
	List<Columnheaders> findByColumntypesProjectsId(Long id);
	
	List<Columnheaders> findByCodeAndColumntypesProjectsId(String code, Long projectId);
}
