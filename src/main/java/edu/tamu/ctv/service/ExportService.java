package edu.tamu.ctv.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

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
	
	
	
	private int _columnSize = 0;
	private int _rowSize = 0;
	private int _verticalOffset = 0;
	private int _horizontalOffset = 0;
	
	private List<List<String>> _resultMap = new ArrayList<List<String>>();
	
	
	//Hibernate mapping
	private Map<Long, Components> componentsMapper = new HashMap<Long, Components>();
	private Map<Long, List<Orders>> ordersMapper  = new HashMap<Long, List<Orders>>();
	private Map<Long, Results> resultsMapper  = new HashMap<Long, Results>();
	private Map<Long, Rowheaders> rowHeaderMapper = new HashMap<Long, Rowheaders>();
	
	//Index mapping
	private Map<Long, Integer> orderIndexMap = new HashMap<Long, Integer>();
	private List<Long> componentPosition = new ArrayList<Long>();
	private List<Long> rowTypePosition = new ArrayList<Long>();
	private List<Long> columnTypePosition = new ArrayList<Long>();
	
	public void ExportByProject(Long projectId)
	{
		
	}
	
	private StringBuffer getStringByIndex(int index)
	{
		StringBuffer sb = new StringBuffer();
		for (String str : _resultMap.get(index))
		{
			if (StringUtils.isBlank(str))
			{
				sb.append(",");			
			}
			else
			{
				sb.append('"').append(str).append('"').append(",");	
			}
			
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("\n");
		return sb;
	}
	
	private void addElementToMap(Long key, String element, Integer position)
	{
		boolean addRowElements = false;
		Integer orderPos = orderIndexMap.get(key);
		if (null == orderPos)
		{
			orderPos = orderIndexMap.size() + _verticalOffset;
			orderIndexMap.put(key, orderPos);
			addRowElements = true;
		}
		List<String> value = _resultMap.get(orderPos);

		if (addRowElements)
		{
			List<Orders> order = ordersMapper.get(key);

			Rowheaders rh = null;
			value.set(0, String.valueOf(key));
			for (Orders o : order)
			{
				rh = rowHeaderMapper.get(o.getRowheaders().getId());
				int rowTypePos = rowTypePosition.indexOf(rh.getRowtypes().getId()) + 1;
				value.set(rowTypePos, rh.getCode());
			}
		}
		
		int elemPos = position + _horizontalOffset;
		value.set(elemPos, element);
	}
	
	private void fillOrderMapper(List<Orders> orderList)
	{
		List<Orders> ol = null;
		for (Orders order : orderList)
		{
			ol = ordersMapper.get(order.getOrderId());
			if (null == ol)
			{
				ol = new ArrayList<Orders>();
				ordersMapper.put(order.getOrderId(), ol);
			}
			ol.add(order);
		}
	}
	
	private void fillResultMapper(List<Results> results)
	{
		for (Results result : results)
		{
			resultsMapper.put(result.getId(), result);
		}
	}
	
	private void fillRowHeaderMapper(List<Rowheaders> rowHeaderList)
	{
		for (Rowheaders rowHeader : rowHeaderList)
		{
			rowHeaderMapper.put(rowHeader.getId(), rowHeader);
		}
	}

	public void ExportByProject(Projects project, HttpServletResponse response) throws IOException
	{
		if (_resultMap != null) _resultMap.clear();
		if (componentsMapper != null) componentsMapper.clear();
		if (ordersMapper != null) ordersMapper.clear();
		if (resultsMapper != null) resultsMapper.clear();
		if (orderIndexMap != null) orderIndexMap.clear();
		if (componentPosition != null) componentPosition.clear();
		if (rowTypePosition != null) rowTypePosition.clear();
		if (rowHeaderMapper != null) rowHeaderMapper.clear();
		
	
		
		Long projectId = project.getId();
		
		String reportName = project.getCode() + ".csv";
		response.setContentType("text/csv");
		response.setHeader("Content-disposition", "attachment;filename=" + reportName);
		
		
		List<Rowtypes> rowTypeList = rowTypesRepository.findByProjectsId(projectId);
		List<Columntypes> columnTypeList = columnTypesRepository.findByProjectsId(projectId);
		List<Rowheaders> rowHeaderList = rowHeadersRepository.findByRowtypesProjectsId(projectId);
		List<Columnheaders> columnHeaderList = columnHeadersRepository.findByColumntypesProjectsId(projectId);
		List<Results> results = resultsRepository.findByProjectsId(projectId);
		List<Components> componentList = componentsRepository.findByProjectsId(projectId);
		List<Orders> orderList = ordersRepository.findOrdersByRowheadersRowtypesProjectsId(projectId);
		
		fillOrderMapper(orderList);
		//fillResultMapper(results);
		fillRowHeaderMapper(rowHeaderList);
		
		Collections.sort(rowTypeList, new Comparator<Rowtypes>()
		{
			public int compare(Rowtypes o1, Rowtypes o2)
			{
				return o1.getShoworder() - o2.getShoworder();
			}
		});
		for (Rowtypes rowtypes : rowTypeList)
		{
			rowTypePosition.add(rowtypes.getId());
		}
		for (Components comp : componentList)
		{
			componentPosition.add(comp.getId());
			componentsMapper.put(comp.getId(), comp);
		}

		Columntypes child = null;
		for (Columntypes columntypes : columnTypeList)
		{
			if (columntypes.getColumntypeses().isEmpty())
			{
				child = columntypes;
				while(child.getColumntypes() != null)
				{
					columnTypePosition.add(child.getId());
					child = child.getColumntypes();
				}
				columnTypePosition = Lists.reverse(columnTypePosition); 
				break;
			}
		}

		
		_verticalOffset = columnTypeList.size() + 1;
		_horizontalOffset = rowTypeList.size() + 1;
		_rowSize = _verticalOffset + ordersMapper.size();
		_columnSize = _horizontalOffset + componentList.size();
		_resultMap = new ArrayList<List<String>>();
		
		//TODO: Change
		for (int i = 0; i < _rowSize; i++)
		{
			List<String> row = new ArrayList<String>();
			row.addAll(Collections.nCopies(_columnSize,""));
			_resultMap.add(row);
		}

		Long componentId = null;
		for (Results result : results)
		{
			componentId = result.getComponents().getId();
			addElementToMap(result.getOrderId(), result.getStrresult(), componentPosition.indexOf(componentId));
		}

		for (int count = 0; count < _resultMap.size(); count++)
		{
			response.getOutputStream().print(getStringByIndex(count).toString());
		}

	}
}
