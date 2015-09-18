package edu.tamu.ctv.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Results;
import edu.tamu.ctv.repository.ResultsRepository;


@Service
public class ResultService
{
	private final Logger logger = LoggerFactory.getLogger(ResultService.class);
	
	@Autowired
	private ResultsRepository resultsRepository;
	
	public boolean updateResultById(Long id, String value)
	{
		Results result = resultsRepository.findOne(id);
		if (result != null)
		{
			result.setStrresult(value);
			resultsRepository.save(result);
		}
		else
		{
			return false;
		}

		return true;
	}
	
	public boolean updateResultByOrderId(Long orderid, String column,  String value)
	{
		logger.debug("updateResultByOrderId() : {}");
		List<Results> results = resultsRepository.findByOrderIdAndComponentsCode(orderid, column);
		if (results.size() > 0)
		{
			results.get(0).setStrresult(value);
		}
		return false;
	}
	
	public boolean updateResultByUnique(List<String> rows, String column, String value)
	{
		
		return true;
	}
}
