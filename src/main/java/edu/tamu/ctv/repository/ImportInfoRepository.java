package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Importinfo;

@Repository
public interface ImportInfoRepository extends CrudRepository<Importinfo, Long> {

}
