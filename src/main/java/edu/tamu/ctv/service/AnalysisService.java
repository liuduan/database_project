package edu.tamu.ctv.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Columnheaders;
import edu.tamu.ctv.entity.Columntypes;
import edu.tamu.ctv.entity.Components;
import edu.tamu.ctv.entity.Orders;
import edu.tamu.ctv.entity.Results;
import edu.tamu.ctv.entity.Rowheaders;
import edu.tamu.ctv.entity.Rowtypes;
import edu.tamu.ctv.entity.customdefined.Analysis;
import edu.tamu.ctv.repository.ColumnHeadersRepository;
import edu.tamu.ctv.repository.ColumnTypesRepository;
import edu.tamu.ctv.repository.ComponentsRepository;
import edu.tamu.ctv.repository.OrdersRepository;
import edu.tamu.ctv.repository.ResultsRepository;
import edu.tamu.ctv.repository.RowHeadersRepository;
import edu.tamu.ctv.repository.RowTypesRepository;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;

@Service("analysisService")
public class AnalysisService
{	
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
	
	public Analysis findResultsByProject(Long id)
	{
		
		Analysis analysis = new Analysis();
		List<Results> results = resultsRepository.findByProjectsId(id);
		List<Components> components = componentsRepository.findByProjectsId(id);
		List<Columnheaders> columnHeaders = columnHeadersReposirory.findByHeaderTypesProjectsId(id);
		List<Columntypes> columnTypes = columnTypesRepository.findByProjectsId(id);
		List<Rowtypes> rowTypes = rowTypesRepository.findByProjectsId(id);
		List<Rowheaders> rowHeaders = rowHeadersRepository.findByRowTypesProjectsId(id);
		List<Orders> orders = ordersRepository.findByRowHeadersRowTypesProjectsId(id);
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
				mResults.put(order.getRowheaders().getRowtypes().getCode(), order.getRowheaders().getCode());
			}
			for(Results result : results) 
			{				
				if (result.getOrderId() != ordId)
				{					
					arrmResults.add(mResults);
					mResults = new HashMap<String, String>();
					ordId = result.getOrderId();
					mResults.put("id", Long.toString(ordId));
					
					filteredOrders = select(orders, having(on(Orders.class).getOrderId(), Matchers.equalTo(ordId)));
					for(Orders order : filteredOrders)
					{
						String str = order.getRowheaders().getCode();						
//						mResults.put(order.getRowheaders().getRowtypes().getCode(), str);
						
						List<Rowheaders> filteredRowHeaders = select(rowHeaders, having(on(Rowheaders.class).getCode(), Matchers.equalTo(str)));
						if (filteredRowHeaders.size() > 0)
						{
							mResults.put(filteredRowHeaders.get(0).getRowtypes().getCode(), str);
						}
					}
				}
				mResults.put(result.getComponents().getCode(), result.getStrresult());
			}
			arrmResults.add(mResults);
			

			ArrayList<ArrayList<Columnheaders>> arrmColumnHeaders  = new ArrayList<ArrayList<Columnheaders>>();
			ArrayList<Columnheaders> arrmColumnHeader  = new ArrayList<Columnheaders>();

			for(Components component : components) 
			{	

				arrmColumnHeader.add(component.getColumnheaders());
			}
			arrmColumnHeaders.add(arrmColumnHeader);
			
			for (int i = 0; i < levelCount - 1; i++)
			{
				arrmColumnHeader  = new ArrayList<Columnheaders>();
				for(Columnheaders Columnheader : arrmColumnHeaders.get(i)) 
				{				        
			        arrmColumnHeader.add(Columnheader.getColumnheaders());			        
			    }
			    arrmColumnHeaders.add(arrmColumnHeader);
			}

			analysis.setResults(arrmResults.toArray((Map<String, String>[]) new Map[arrmResults.size()]));
		

			
			analysis.setColumnHeaders(arrmColumnHeaders);
			
			analysis.setComponents(components);
			
			analysis.setRowTypes(rowTypes);
			
		}
		
		
		
		return analysis;
	}	
}
