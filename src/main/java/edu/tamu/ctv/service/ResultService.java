package edu.tamu.ctv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.repository.ResultsRepository;


@Service
public class ResultService
{
	@Autowired
	private ResultsRepository resultsRepository;
	

}
