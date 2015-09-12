package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Columnheaders;

@Repository
public interface CoulumnHeadersRepository extends CrudRepository<Columnheaders, Long> {

}
