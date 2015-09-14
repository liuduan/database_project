package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Resultshistory;

@Repository
public interface ResultsHistoryRepository extends CrudRepository<Resultshistory, Long> {

}
