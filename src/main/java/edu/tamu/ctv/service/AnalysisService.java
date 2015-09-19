package edu.tamu.ctv.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Date;

import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import edu.tamu.ctv.entity.Columnheaders;
import edu.tamu.ctv.entity.Columntypes;
import edu.tamu.ctv.entity.Components;
import edu.tamu.ctv.entity.Orders;
import edu.tamu.ctv.entity.Projects;
import edu.tamu.ctv.entity.Results;
import edu.tamu.ctv.entity.Rowheaders;
import edu.tamu.ctv.entity.Rowtypes;
import edu.tamu.ctv.entity.customdefined.Analysis;
import edu.tamu.ctv.entity.customdefined.AnalysisResults;
import edu.tamu.ctv.repository.ColumnHeadersRepository;
import edu.tamu.ctv.repository.ColumnTypesRepository;
import edu.tamu.ctv.repository.ComponentsRepository;
import edu.tamu.ctv.repository.OrdersRepository;
import edu.tamu.ctv.repository.ResultsRepository;
import edu.tamu.ctv.repository.RowHeadersRepository;
import edu.tamu.ctv.repository.RowTypesRepository;
import edu.tamu.ctv.utils.Constant;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;

@Service("analysisService")
public class AnalysisService
{	
	private final Logger logger = LoggerFactory.getLogger(AnalysisService.class);
	
	@Autowired
	private ResultsRepository resultsRepository;
	
	@Autowired
	private ComponentsRepository componentsRepository;
	
	@Autowired
	private ColumnHeadersRepository columnHeadersReposirory;
	
	@Autowired
	private ColumnTypesRepository columnTypesRepository;
	
	@Autowired
	private RowTypesRepository rowTypesRepository;
	
	@Autowired
	private RowHeadersRepository rowHeadersRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	private Map<Long, Rowtypes> rowTypesMapper = new HashMap<Long, Rowtypes>();
	private Map<Long, Rowheaders> rowHeaderMapper = new HashMap<Long, Rowheaders>();
	private Map<Long, Columntypes> columnTypesMapper = new HashMap<Long, Columntypes>();
	private Map<Long, Columnheaders> columnHeaderMapper = new HashMap<Long, Columnheaders>();
	private Map<Long, Components> componentsMapper = new HashMap<Long, Components>();
	
	
	private void fillColumnTypeMapper(List<Columntypes> columnTypesList)
	{
		for (Columntypes columnType : columnTypesList)
		{
			columnTypesMapper.put(columnType.getId(), columnType);
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
	
	private void fillRowTypeMapper(List<Rowtypes> rowTypeList)
	{
		for (Rowtypes rowType : rowTypeList)
		{
			rowTypesMapper.put(rowType.getId(), rowType);
		}
	}
	
	private void fillComponentsMapper(List<Components> componentsList)
	{
		for (Components component : componentsList)
		{
			componentsMapper.put(component.getId(), component);
		}
	}
	
	public Analysis findResultsByProject(Long id)
	{
		if (rowTypesMapper != null) rowTypesMapper.clear();
		if (rowHeaderMapper != null) rowHeaderMapper.clear();
		if (columnTypesMapper != null) columnTypesMapper.clear();
		if (columnHeaderMapper != null) columnHeaderMapper.clear();
		if (componentsMapper != null) componentsMapper.clear();
		
		Analysis analysis = new Analysis();
		List<Results> results = resultsRepository.findByProjectsId(id);
		List<Components> components = componentsRepository.findByProjectsId(id);
		List<Columnheaders> columnHeaders = columnHeadersReposirory.findByHeaderTypesProjectsId(id);
		List<Columntypes> columnTypes = columnTypesRepository.findByProjectsId(id);
		List<Rowtypes> rowTypes = rowTypesRepository.findByProjectsId(id);
		List<Rowheaders> rowHeaders = rowHeadersRepository.findByRowTypesProjectsId(id);
		List<Orders> orders = ordersRepository.findByRowHeadersRowTypesProjectsId(id);
		
		fillColumnTypeMapper(columnTypes);
		fillColumnHeaderMapper(columnHeaders);
		fillRowTypeMapper(rowTypes);
		fillRowHeaderMapper(rowHeaders);
		fillComponentsMapper(components);
		
		int levelCount = (columnTypes!=null && columnTypes.size() > 0) ? columnTypes.size() : 0;
		
		if (results.size() > 0 && components.size() > 0)
		{
			Collections.sort(results, new Comparator<Results>() {
	
		        public int compare(Results o1, Results o2) {
		            return o1.getOrderId().compareTo(o2.getOrderId());
		        }
		    });
			
			//-----ADD NEW RESULT-ROW--------------
			
			ArrayList<Map<String, String>> arrmResults  = new ArrayList<Map<String, String>>();
			 Map<String, String> mResults = new HashMap<String, String>();
			 Long  ordId = results.get(0).getOrderId();
			mResults.put("id", Long.toString(ordId));
			
			List<Orders> filteredOrders = select(orders, having(on(Orders.class).getOrderId(), Matchers.equalTo(ordId)));
			for(Orders order : filteredOrders)
			{//filteredOrders.get(1).getRowheaders().getCode();
				Rowheaders rh = rowHeaderMapper.get(order.getRowheaders().getId());
				Rowtypes rt = rowTypesMapper.get(rh.getRowtypes().getId());
				mResults.put(rt.getCode(), rh.getCode());
			}
			for(Results result : results) 
			{				
				if (!result.getOrderId().equals(ordId))
				{		 
					arrmResults.add(mResults);
					mResults = new HashMap<String, String>();
					ordId = result.getOrderId();
					mResults.put("id", Long.toString(ordId));
					
					filteredOrders = select(orders, having(on(Orders.class).getOrderId(), Matchers.equalTo(ordId)));
					for(Orders order : filteredOrders)
					{
						Rowheaders rh = rowHeaderMapper.get(order.getRowheaders().getId());
						String str = rh.getCode();						
//						mResults.put(order.getRowheaders().getRowtypes().getCode(), str);
						
						List<Rowheaders> filteredRowHeaders = select(rowHeaders, having(on(Rowheaders.class).getCode(), Matchers.equalTo(str)));
						if (filteredRowHeaders.size() > 0)
						{
							Rowtypes rt = rowTypesMapper.get(filteredRowHeaders.get(0).getRowtypes().getId());
							mResults.put(rt.getCode(), str);
						}
					}
				}
				Components c = componentsMapper.get(result.getComponents().getId());
				mResults.put(c.getCode(), result.getStrresult());
			}
			arrmResults.add(mResults);
			

			ArrayList<ArrayList<Columnheaders>> arrmColumnHeaders  = new ArrayList<ArrayList<Columnheaders>>();
			ArrayList<Columnheaders> arrmColumnHeader  = new ArrayList<Columnheaders>();
			ArrayList<Map<String, String>> arrmColumnHeaderResults  = new ArrayList<Map<String, String>>();
			
			for(Components component : components) 
			{	
				Columnheaders columnHeader = columnHeaderMapper.get(component.getColumnheaders().getId());
				arrmColumnHeader.add(columnHeader);
				
				//Column headers results for split second grid
				Map<String, String> columnResult = new HashMap<String, String>();
				columnResult.put("COMPONENT", component.getCode());
				columnResult.put("id", component.getId().toString());
				while (columnHeader != null)
				{
					Columntypes ct = columnTypesMapper.get(columnHeader.getColumntypes().getId());
					columnResult.put(ct.getCode(), columnHeader.getCode());
					if (columnHeader.getColumnheaders() != null)
					{
						columnHeader = columnHeaderMapper.get(columnHeader.getColumnheaders().getId());	
					}
					else
					{
						columnHeader = null;
					}
					
				}
				arrmColumnHeaderResults.add(columnResult);
			}
			arrmColumnHeaders.add(arrmColumnHeader);
			
			for (int i = 0; i < levelCount - 1; i++)
			{
				arrmColumnHeader  = new ArrayList<Columnheaders>();
				for(Columnheaders Columnheader : arrmColumnHeaders.get(i)) 
				{
			        arrmColumnHeader.add(columnHeaderMapper.get(Columnheader.getColumnheaders().getId()));			        
			    }
			    arrmColumnHeaders.add(arrmColumnHeader);
			}
			
			
			
			
			

			analysis.setResults(arrmResults.toArray((Map<String, String>[]) new Map[arrmResults.size()]));
		
			analysis.setColumnHeaderResults(arrmColumnHeaderResults.toArray((Map<String, String>[]) new Map[arrmColumnHeaderResults.size()]));
			
			analysis.setColumnHeaders(arrmColumnHeaders);
			
			analysis.setComponents(components);
			
			analysis.setRowTypes(rowTypes);
			
			columnTypes.add(0, new Columntypes(new Long(0), null, null, "COMPONENT", "COMPONENT", new Date()));
			analysis.setColumnTypes(columnTypes);
			
		}
		
		
		
		return analysis;
	}	
	
	public AnalysisResults getResultsForAnalysis(List<Long> orderid, List<Long> componentid)
	{
		AnalysisResults result = new AnalysisResults();
		
		List<Results> results = null;
	
		List<Long> projectIdList = new ArrayList<Long>();
		
		if (null == orderid)
		{
			orderid = new ArrayList<Long>();
		}
		
		if (null == componentid)
		{
			componentid = new ArrayList<Long>();
		}
		
		if (orderid.size() > 0 && componentid.size() > 0)
		{
			results = resultsRepository.findByOrderIdInAndComponentsIdIn(orderid, componentid);
		}
		else if (orderid.size() > 0)
		{
			results = resultsRepository.findByOrderIdIn(orderid);
		}
		else if (componentid.size() > 0)
		{
			results = resultsRepository.findByComponentsIdIn(componentid);
		}
		
		if (results != null && results.size() > 0)
		{
			List<Rowtypes> rowTypeList = null;
			//List<Columntypes> columnTypeList = null;
			Map<Long, List<Orders>> ordersMapper  = new HashMap<Long, List<Orders>>();
			Map<Long, Rowtypes> rowTypeMapper = new HashMap<Long, Rowtypes>();
			Map<Long, Rowheaders> rowHeaderMapper = new HashMap<Long, Rowheaders>();
			Map<Long, Components> componentsMapper = new HashMap<Long, Components>();

			for (Results res : results)
			{
				if (!projectIdList.contains(res.getProjects().getId()))
				{
					projectIdList.add(res.getProjects().getId());					
				}
			}
			if (orderid.size() == 0)
			{
				for (Results res : results)
				{
					if (!orderid.contains(res.getOrderId())) orderid.add(res.getOrderId());
				}
			}

			List<Components> components = (componentid.size() > 0) ? componentsRepository.findByIdIn(componentid) : componentsRepository.findByProjectsIdIn(projectIdList);
			List<Orders> orderList = ordersRepository.findByOrderIdIn(orderid);
			
			fillOrderMapper(ordersMapper, orderList);
			
			for (Components comp : components)
			{
				componentsMapper.put(comp.getId(), comp);
			}
			List<Long> tmpRHIds = new ArrayList<Long>();
			for (Orders orders : orderList)
			{
				tmpRHIds.add(orders.getRowheaders().getId());
			}
			List<Long> tmpRTIds = new ArrayList<Long>();
			List<Rowheaders> tmpRHList = rowHeadersRepository.findByIdIn(tmpRHIds);
			for (Rowheaders rh : tmpRHList)
			{
				tmpRTIds.add(rh.getRowtypes().getId());
				rowHeaderMapper.put(rh.getId(), rh);
				
			}
			
			//columnTypeList = columnTypesRepository.findByProjectsIdIn(projectIdList);
			rowTypeList = rowTypesRepository.findByIdIn(tmpRTIds);
			Collections.sort(rowTypeList, new Comparator<Rowtypes>()
			{
		        public int compare(Rowtypes o1, Rowtypes o2)
		        {
		        	int project = o1.getProjects().getId().compareTo(o2.getProjects().getId());
		        	if (0 != project)
		        	{
		        		return project;
		        	}
		            return o1.getShoworder() - o2.getShoworder();
		        }
		    });



			result.getColumnCodeList().add(Constant.ID);
			for (Rowtypes rt : rowTypeList)
			{
				result.getColumnCodeList().add(rt.getCode());
				rowTypeMapper.put(rt.getId(), rt);
			}
			//TODO: Change the code
			result.getColumnCodeList().add("Component");
			result.getColumnCodeList().add("Value");
			
			Map<String, String> resMap = null;
			List<Map<String, String>> resultsList = result.getResultValueList();
			for (Results res : results)
			{
				resMap = new HashMap<String, String>();
				resMap.put(Constant.ID, String.valueOf(res.getId()));
				resMap.put("Component", componentsMapper.get(res.getComponents().getId()).getCode());
				resMap.put("Value", res.getStrresult());
				
				List<Orders> tmpOrders = ordersMapper.get(res.getOrderId());
				if (tmpOrders != null)
				{
					for (Orders order : tmpOrders)
					{
						Long rhid = order.getRowheaders().getId();
						Rowheaders rh = rowHeaderMapper.get(rhid);
						resMap.put(rowTypeMapper.get(rh.getRowtypes().getId()).getCode(), rowHeaderMapper.get(rhid).getCode());
						
					}
				}

				resultsList.add(resMap);
			}
		}

		
		return result;
	}
	
	private void fillOrderMapper(Map<Long, List<Orders>> ordersMapper, List<Orders> orderList)
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
}
