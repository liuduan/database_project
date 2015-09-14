package edu.tamu.ctv.repository;

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
	@Query("SELECT r FROM Results r WHERE r.orderId = :id")
	List<Results> getResultsByOrderId(@Param("id")Long id);
}
