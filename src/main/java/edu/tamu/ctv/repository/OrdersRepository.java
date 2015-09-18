package edu.tamu.ctv.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Orders;
import edu.tamu.ctv.entity.Results;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long>
{
	List<Orders> findByOrderIdIn(Collection<Long> ids);

	@Query("SELECT r FROM Results r WHERE r.orderId = :id")
	List<Results> getResultsByOrderId(@Param("id")Long id);
	
	@Query("select res from Orders as res inner join res.rowheaders rh inner join rh.rowtypes rt inner join rt.projects p where p.id = :id")
	List<Orders> findByRowHeadersRowTypesProjectsId(@Param("id")Long id);
	
	List<Orders> findOrdersByRowheadersRowtypesProjectsId(Long id);
	
}
