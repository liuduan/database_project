package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Viewtemplates;

@Repository
public interface ViewTemplatesRepository extends CrudRepository<Viewtemplates, Long> {

}
