package edu.tamu.ctv.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Columnheaders;
import edu.tamu.ctv.entity.Columntypes;
import edu.tamu.ctv.entity.Components;
import edu.tamu.ctv.entity.Orders;
import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.entity.Results;
import edu.tamu.ctv.entity.Rowheaders;
import edu.tamu.ctv.entity.Rowtypes;
import edu.tamu.ctv.repository.ColumnHeadersRepository;
import edu.tamu.ctv.repository.ColumnTypesRepository;
import edu.tamu.ctv.repository.ComponentsRepository;
import edu.tamu.ctv.repository.OrdersRepository;
import edu.tamu.ctv.repository.ProjectsRepository;
import edu.tamu.ctv.repository.ResultsRepository;
import edu.tamu.ctv.repository.RowHeadersRepository;
import edu.tamu.ctv.repository.RowTypesRepository;


@Service("exportService")
public class ExportService
{
	private final Logger logger = LoggerFactory.getLogger(ExportService.class);
	
	@Autowired
	private ProjectsRepository projectRepository;
	@Autowired
	private RowTypesRepository rowTypesRepository;
	@Autowired
	private RowHeadersRepository rowHeadersRepository;
	@Autowired
	private ColumnTypesRepository columnTypesRepository;
	@Autowired
	private ColumnHeadersRepository columnHeadersRepository;
	@Autowired
	private ComponentsRepository componentsRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private ResultsRepository resultsRepository;
	
	private List<List<String>> resultMap = null;
	private Map<String, Integer> rowPosition = null;
	private Map<String, Integer> columnPosition = null;
	private int columnSize = 0;
	private int rowSize = 0;
	
	public void ExportByProject(Long projectId)
	{
		
	}
	
	private StringBuffer getStringByIndex(int index)
	{
		StringBuffer sb = new StringBuffer();
		for (String str : resultMap.get(index))
		{
			sb.append(str).append(",");
		}
		sb.deleteCharAt(sb.length());
		sb.append("\n");
		return sb;
	}
	
	private void addElementToMap(Long key, String element, Integer position)
	{
		List<String> value = resultMap.get(key.intValue());
		if (null == value)
		{
			value = new ArrayList<String>(columnSize);
			resultMap.set(key.intValue(), value);
		}
		value.set(position, element);
	}
	
	public void ExportByProject(Projects project, HttpServletResponse response) throws IOException
	{
		Long projectId = project.getId();
		
		String reportName = project.getCode() + ".csv";
		response.setContentType("text/csv");
		response.setHeader("Content-disposition", "attachment;filename=" + reportName);
		
		
		

		
/*		if (rowPosition != null) rowPosition.clear();
		rowPosition = new HashMap<String, Integer>();
		
		if (columnPosition != null) columnPosition.clear();
		columnPosition = new HashMap<String, Integer>();
		
		List<Rowtypes> rowTypeList = rowTypesRepository.findByProjectsId(projectId);
		for (Rowtypes rowtypes : rowTypeList)
		{
			rowPosition.put(rowtypes.getCode(), rowtypes.getShoworder());
		}
		List<Rowheaders> rowHeaderList = rowHeadersRepository.findByRowHeadersProjectsId(projectId);
		
		List<Columntypes> columnTypeList = columnTypesRepository.findByProjectsId(projectId);
		Columntypes child = null;
		for (Columntypes columntypes : columnTypeList)
		{
			if (columntypes.getColumntypeses().isEmpty())
			{
				child = columntypes;
				break;
			}
		}
		int position = 1;
		while(child.getColumntypes() != null)
		{
			columnPosition.put(child.getCode(), position++);
			child = child.getColumntypes();
		}
		
		List<Columnheaders> columnHeaderList = columnHeadersRepository.findByColumntypesProjectsId(projectId);
		List<Orders> orderList = ordersRepository.findOrdersByRowheadersRowtypesProjectsId(projectId);*/
		

		
		List<Rowtypes> rowTypeList = rowTypesRepository.findByProjectsId(projectId);
		List<Columntypes> columnTypeList = columnTypesRepository.findByProjectsId(projectId);
		
		List<Components> componentList = componentsRepository.findByProjectsId(projectId);
		List<Results> results = resultsRepository.findByProjectsId(projectId);
		
		int verticalOffset = columnTypeList.size();
		int horizontalOffset = rowTypeList.size();
		
		rowSize = verticalOffset + results.size();
		columnSize = horizontalOffset + componentList.size();
		
		if (resultMap != null) resultMap.clear();
		resultMap = new ArrayList<List<String>>(results.size());
		
		for (Results result : results)
		{
			addElementToMap(result.getOrderId() + verticalOffset, result.getStrresult(), componentList.indexOf(result.getComponents()) + verticalOffset);
		}
		for (int count = 0; count < resultMap.size(); count++)
		{
			response.getOutputStream().print(getStringByIndex(count).toString());
		}

	}
}
