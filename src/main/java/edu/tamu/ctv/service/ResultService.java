package edu.tamu.ctv.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Results;
import edu.tamu.ctv.repository.ResultsRepository;

@Service
public class ResultService
{
	private final Logger logger = LoggerFactory.getLogger(ResultService.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

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

	public boolean updateResultByOrderId(Long orderid, String column, String value)
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

	public SqlRowSet findResultsForExportByProjectId(Long projectId)
	{
		//List<Object[]> result = new ArrayList<Object[]>();
		try
		{
			logger.debug("findResultsForExportByProjectId()");

			String query = "select order_id, strresult, component_id from results where project_id = ?";
			Object[] parameterList = { projectId };
			return jdbcTemplate.queryForRowSet(query, parameterList);

/*			if (dataRow.next())
			{
				result.add(dataRow.getLong("order_id"), dataRow.getString("strresult"), dataRow.getLong("component_id"));
			}*/
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
