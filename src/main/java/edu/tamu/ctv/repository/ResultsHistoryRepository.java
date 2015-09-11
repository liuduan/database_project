package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.tamu.ctv.entity.Resultshistory;

@Component
public interface ResultsHistoryRepository extends CrudRepository<Resultshistory, Long> {

}
