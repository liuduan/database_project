package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Rowheaders;

@Component
public interface RowHeadersRepository extends CrudRepository<Rowheaders, Long> {

}
