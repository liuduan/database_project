package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Units;

@Component
public interface UnitsRepository extends CrudRepository<Units, Long> {

}
