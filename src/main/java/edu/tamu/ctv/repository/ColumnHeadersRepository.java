package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Columnheaders;

@Component
public interface ColumnHeadersRepository extends CrudRepository<Columnheaders, Long> {

}
