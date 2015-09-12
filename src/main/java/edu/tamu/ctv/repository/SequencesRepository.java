package edu.tamu.ctv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.ctv.entity.Sequences;

@Repository
public interface SequencesRepository extends CrudRepository<Sequences, Long>
{
}
