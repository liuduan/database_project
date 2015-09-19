package edu.tamu.ctv.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Rowheaders;

@Repository
public interface RowHeadersRepository extends CrudRepository<Rowheaders, Long>
{
	List<Rowheaders> findByIdIn(Collection<Long> ids);
	
	@Query("from Rowheaders as res inner join res.rowtypes rt inner join rt.projects p where res.code = :code and p.code = :projectCode")
	List<Rowheaders> findByCodeAndRowTypesProjectsCode(@Param("code")String code, @Param("projectCode")String projectCode);
	
	@Query("select res from Rowheaders as res inner join res.rowtypes ct inner join ct.projects p where p.id = :id")
	List<Rowheaders> findByRowTypesProjectsId(@Param("id")Long id);

	List<Rowheaders> findByRowtypesProjectsId(Long id);
	List<Rowheaders> findByRowtypesProjectsIdIn(Collection<Long> ids);
	List<Rowheaders> findByRowtypesIdIn(Collection<Long> ids);
	List<Rowheaders> findByOrdersesIdIn(Collection<Long> ids);
	
	List<Rowheaders> findByCodeAndRowtypesIdAndRowtypesProjectsId(String code, Long rowTypeId, Long projectId);
}
