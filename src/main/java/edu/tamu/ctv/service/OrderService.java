package edu.tamu.ctv.service;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderService
{
	private Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean getOrdersByProjectId(Long id)
	{
		try
		{
			logger.debug("isEmployeeExists()");

			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT o FROM Orders o INNER JOIN Results r ON o.order_id = r.order_id WHERE r.project_id = ?");
			Object[] parameterList = { id };
			SqlRowSet dataRow = jdbcTemplate.queryForRowSet(queryString.toString(), parameterList);

			if (dataRow.next())
			{
				Object obj = dataRow;
			}

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return false;
	}

}
