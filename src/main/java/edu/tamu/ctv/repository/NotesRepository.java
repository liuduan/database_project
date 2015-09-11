package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Notes;

@Component
public interface NotesRepository extends CrudRepository<Notes, Long> {

}
