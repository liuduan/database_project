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
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
import edu.tamu.ctv.repository.ResultsRepository;
import edu.tamu.ctv.repository.RowHeadersRepository;
import edu.tamu.ctv.repository.RowTypesRepository;


@Service("exportService")
public class ExportService
{
	private final Logger logger = LoggerFactory.getLogger(ExportService.class);
	
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
	@Autowired
	private ResultService resultService;
	
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
	private Map<Long, Columnheaders> columnHeaderMapper = new HashMap<Long, Columnheaders>();
	
	//Index mapping
	private Map<Long, Integer> orderIndexMap = new HashMap<Long, Integer>();
	private List<Long> columnIndexMap = new ArrayList<Long>();
	
	private List<Long> componentPosition = new ArrayList<Long>();
	private List<Long> rowTypePosition = new ArrayList<Long>();
	private List<Long> columnTypePosition = new ArrayList<Long>();
	
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
	
	private void addRowHeaderToMap(List<Rowtypes> rowTypeList)
	{
		List<String> value = _resultMap.get(_verticalOffset - 1);
		value.set(0, "row_order");
		for (int i = 0; i < rowTypeList.size(); i++)
		{
			value.set(i + 1, rowTypeList.get(i).getCode());
		}

	}
	
	private void addElementToMap(Long key, String element, Long componentId)
	{
		Components component = null;
		boolean addRowElements = false;
		int position = componentPosition.indexOf(componentId);
		int elemPos = position + _horizontalOffset;
		Integer orderPos = orderIndexMap.get(key);
		
		if (null == orderPos)
		{
			orderPos = orderIndexMap.size() + _verticalOffset;
			orderIndexMap.put(key, orderPos);
			addRowElements = true;
		}
		List<String> value = _resultMap.get(orderPos);

		//Rows
		if (addRowElements)
		{
			List<Orders> order = ordersMapper.get(key);
			Rowheaders rh = null;
			value.set(0, String.valueOf(orderPos - _verticalOffset + 1));
			if (order != null)
			{
				for (Orders o : order)
				{
					rh = rowHeaderMapper.get(o.getRowheaders().getId());
					int rowTypePos = rowTypePosition.indexOf(rh.getRowtypes().getId()) + 1;
					value.set(rowTypePos, rh.getCode());
				}
			}

		}
		
		//Columns
		if (!columnIndexMap.contains(componentId))
		{
			int headerPos = _verticalOffset - 1;
			columnIndexMap.add(componentId);
			component = componentsMapper.get(componentId);
			
			List<String> componentValue = _resultMap.get(headerPos);
			componentValue.set(elemPos, component.getCode());
			Columnheaders ch = columnHeaderMapper.get(component.getColumnheaders().getId());
			while (ch != null)
			{
				headerPos--;
				componentValue = _resultMap.get(headerPos);
				componentValue.set(elemPos, ch.getCode());
				if (ch.getColumnheaders() != null)
				{
					ch = columnHeaderMapper.get(ch.getColumnheaders().getId());					
				}
				else
				{
					ch = null;
				}
			}
		}
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
	
	private void fillColumnHeaderMapper(List<Columnheaders> columnHeaderList)
	{
		for (Columnheaders columnHeader : columnHeaderList)
		{
			columnHeaderMapper.put(columnHeader.getId(), columnHeader);
		}
	}
	
	private void fillRowHeaderMapper(List<Rowheaders> rowHeaderList)
	{
		for (Rowheaders rowHeader : rowHeaderList)
		{
			rowHeaderMapper.put(rowHeader.getId(), rowHeader);
		}
	}
	
	public void ExportByProject(Projects project, List<Long> orderidfilter, List<Long> componentidfilter, HttpServletResponse response) throws IOException
	{
		ClearResources();
		
		List<Components> componentList = null;
		List<Orders> orderList = null;
		
		if (null == project)
		{
			componentList = componentsRepository.findByIdIn(componentidfilter);
			if (componentList.size() > 0)
			{
				project = componentList.get(0).getProjects();
			}
			else
			{
				orderList = ordersRepository.findByOrderIdIn(orderidfilter);
				if (orderList.size() > 0)
				{
					project = orderList.get(0).getRowheaders().getRowtypes().getProjects();
				}
				else
				{
					return;
				}
			}
		}
		Long projectId = project.getId();
		
		String reportName = project.getCode() + ".csv";
		response.setContentType("text/csv");
		response.setHeader("Content-disposition", "attachment;filename=" + reportName);
		
		
		List<Rowtypes> rowTypeList = rowTypesRepository.findByProjectsId(projectId);
		List<Columntypes> columnTypeList = columnTypesRepository.findByProjectsId(projectId);
		List<Rowheaders> rowHeaderList = rowHeadersRepository.findByRowtypesProjectsId(projectId);
		List<Columnheaders> columnHeaderList = columnHeadersRepository.findByColumntypesProjectsId(projectId);
		//List<Results> results = resultsRepository.findByProjectsId(projectId);
		SqlRowSet results = resultService.findResultsForExportByProjectId(projectId);

		
		if (componentidfilter != null && componentidfilter.size() > 0)
		{
			if (null == componentList)
			{
				componentList = componentsRepository.findByIdIn(componentidfilter);				
			}
		}
		else
		{
			componentList = componentsRepository.findByProjectsId(projectId);			
		}
		
		
		if (orderidfilter != null && orderidfilter.size() > 0)
		{
			if (null == orderList)
			{
				orderList = ordersRepository.findByOrderIdIn(orderidfilter);				
			}
		}
		else
		{
			orderList = ordersRepository.findOrdersByRowheadersRowtypesProjectsId(projectId);
		}

		
		fillOrderMapper(orderList);
		fillRowHeaderMapper(rowHeaderList);
		fillColumnHeaderMapper(columnHeaderList);
		
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
				Collections.reverse(columnTypePosition);
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
		
		try
		{
			addRowHeaderToMap(rowTypeList);
			//for (Object[] result : results)
			while (results.next())
			{
				Long tmpOrdId = results.getLong("order_id");
				Long tmpCompId = results.getLong("component_id");
				if (ordersMapper.containsKey(tmpOrdId) && componentsMapper.containsKey(tmpCompId))
				{
					addElementToMap(tmpOrdId , results.getString("strresult"), tmpCompId);
				}
				//addElementToMap(result.getOrderId(), result.getStrresult(), result.getComponents().getId());
			}

			for (int count = 0; count < _resultMap.size(); count++)
			{
				response.getOutputStream().print(getStringByIndex(count).toString());
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		finally
		{
			ClearResources();	
		}
	}
	
	private void ClearResources()
	{
		if (_resultMap != null) _resultMap.clear();
		if (componentsMapper != null) componentsMapper.clear();
		if (ordersMapper != null) ordersMapper.clear();
		if (resultsMapper != null) resultsMapper.clear();
		if (orderIndexMap != null) orderIndexMap.clear();
		if (componentPosition != null) componentPosition.clear();
		if (rowTypePosition != null) rowTypePosition.clear();
		if (rowHeaderMapper != null) rowHeaderMapper.clear();
		if (columnHeaderMapper != null) columnHeaderMapper.clear();
		if (columnIndexMap != null) columnIndexMap.clear();
	}
}
