package edu.tamu.ctv.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Components;

@Repository("componentsRepository")
public interface ComponentsRepository extends CrudRepository<Components, Long>
{
	List<Components> findByProjectsId(Long id);
	List<Components> findByIdIn(Collection<Long> ids);
	List<Components> findByProjectsIdIn(Collection<Long> ids);
	Components findByCodeAndProjectsCode(String code, String pCode);
}
