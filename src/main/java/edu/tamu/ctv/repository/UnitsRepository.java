package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Units;

@Repository
public interface UnitsRepository extends CrudRepository<Units, Long> {

}
