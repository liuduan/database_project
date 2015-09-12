package edu.tamu.ctv.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Orders;

@Component
public interface OrdersRepository extends CrudRepository<Orders, Long>
{
	//TODO: Chabge
	@Query("select nextval('orders_id_seq') as result")
	public Long getNextOrderId();
}
