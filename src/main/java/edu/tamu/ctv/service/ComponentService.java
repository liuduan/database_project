package edu.tamu.ctv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.repository.ComponentsRepository;

@Service("componentService")
public class ComponentService
{
	@Autowired
	private ComponentsRepository componentsRepository;
	
	public ComponentService()
	{
		
	}

	public ComponentsRepository getComponentsRepository()
	{
		return componentsRepository;
	}
}
