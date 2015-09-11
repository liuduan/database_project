package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Viewtemplates;

@Component
public interface ViewTemplatesRepository extends CrudRepository<Viewtemplates, Long> {

}
