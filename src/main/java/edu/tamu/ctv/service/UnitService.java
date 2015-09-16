package edu.tamu.ctv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.Units;
import edu.tamu.ctv.repository.UnitsRepository;

@Service
public class UnitService
{
	@Autowired
	private UnitsRepository unitsRepository;
	
	public Units getDefaultUnit()
	{
		return unitsRepository.findOne(1l);
	}
}
