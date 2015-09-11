package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Orders;

@Component
public interface OrdersRepository extends CrudRepository<Orders, Long> {

}
