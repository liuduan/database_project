package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Orders;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long>
{
}
