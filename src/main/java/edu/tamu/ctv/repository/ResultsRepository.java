package edu.tamu.ctv.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Results;

@Repository
public interface ResultsRepository extends CrudRepository<Results, Long>
{
	List<Results> findByProjectsId(Long id);
	
	List<Results> findByOrderId(Long id);
	
	List<Results> findByOrderIdAndComponentsCode(Long id, String componentCode);
	
	List<Results> findByOrderIdIn(Collection<Long> orderid);
	
	List<Results> findByComponentsIdIn(Collection<Long> componentid);

	List<Results> findByOrderIdInAndComponentsIdIn(Collection<Long> orderid, Collection<Long> componentid);
}
