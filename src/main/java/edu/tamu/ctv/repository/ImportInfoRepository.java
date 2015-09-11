package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Importinfo;

@Component
public interface ImportInfoRepository extends CrudRepository<Importinfo, Long> {

}
