package edu.tamu.ctv.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Components;
import edu.tamu.ctv.entity.Results;
import edu.tamu.ctv.entity.customdefined.Analysis;
import edu.tamu.ctv.repository.ComponentsRepository;
import edu.tamu.ctv.repository.ResultsRepository;

@Service("analysisService")
public class AnalysisService
{	
	@Autowired
	private ResultsRepository resultsRepository;
	
	@Autowired
	private ComponentsRepository componentsRepository;
	
	public Analysis findResultsByProject(Long id)
	{
		Analysis analysis = new Analysis();
		List<Results> results = resultsRepository.findByProjectsId(id);
		List<Components> components = componentsRepository.findByProjectsId(id);
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
			
			ArrayList<String> arrComponents  = new ArrayList<String>();
			for(Components component : components) 
			{	
				arrComponents.add(component.getCode());
			}

			analysis.setResults(arrmResults.toArray((Map<String, String>[]) new Map[arrmResults.size()]));
		
			analysis.setColumns(arrComponents.toArray(new String[arrComponents.size()]));
			
		}
		
		
		
		return analysis;
	}	
}
