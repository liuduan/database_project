package edu.tamu.ctv.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cglib.core.Predicate;

import edu.tamu.ctv.entity.Columnheaders;
import edu.tamu.ctv.entity.Columntypes;
import edu.tamu.ctv.entity.Components;
import edu.tamu.ctv.entity.Results;
import edu.tamu.ctv.entity.customdefined.Analysis;
import edu.tamu.ctv.repository.ColumnHeadersRepository;
import edu.tamu.ctv.repository.ColumnTypesRepository;
import edu.tamu.ctv.repository.ComponentsRepository;
import edu.tamu.ctv.repository.ResultsRepository;

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
	
	public Analysis findResultsByProject(Long id)
	{
		Analysis analysis = new Analysis();
		List<Results> results = resultsRepository.findByProjectsId(id);
		List<Components> components = componentsRepository.findByProjectsId(id);
		List<Columnheaders> columnHeaders = columnHeadersReposirory.findByHeaderTypesProjectsId(id);
		List<Columntypes> columnTypes = columnTypesRepository.findByProjectsId(id);
		int levelCount = (columnTypes!=null && columnTypes.size() > 0) ? columnTypes.size() : 0;
		columnHeaders.get(0).getCode();
		if (results.size() > 0 && components.size() > 0)
		{
			Collections.sort(results, new Comparator<Results>() {
	
		        public int compare(Results o1, Results o2) {
		            return o1.getOrderId().compareTo(o2.getOrderId());
		        }
		    });
			
			ArrayList<Map<String, String>> arrmResults  = new ArrayList<Map<String, String>>();
			 Map<String, String> mResults = new HashMap<String, String>();
			Long ordId = results.get(0).getOrderId();
			mResults.put("id", Long.toString(ordId));
			for(Results result : results) 
			{				
				if (result.getOrderId() != ordId)
				{					
					arrmResults.add(mResults);
					mResults = new HashMap<String, String>();
					ordId = result.getOrderId();
					mResults.put("id", Long.toString(ordId));
				}
				mResults.put(result.getComponents().getCode(), result.getStrresult());
			}
			arrmResults.add(mResults);
			

			ArrayList<ArrayList<Columnheaders>> arrmColumnHeaders  = new ArrayList<ArrayList<Columnheaders>>();
			ArrayList<Columnheaders> arrmColumnHeader  = new ArrayList<Columnheaders>();
			ArrayList<String> arrComponents  = new ArrayList<String>();
			for(Components component : components) 
			{	
				arrComponents.add(component.getCode());
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
		
			analysis.setColumns(arrComponents.toArray(new String[arrComponents.size()]));
			
			analysis.setColumnHeaders(arrmColumnHeaders);
			
			analysis.setComponents(components);
			
		}
		
		
		
		return analysis;
	}	
}
