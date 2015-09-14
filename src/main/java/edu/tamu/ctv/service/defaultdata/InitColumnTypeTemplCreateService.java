package edu.tamu.ctv.service.defaultdata;

import java.util.ArrayList;
import java.util.List;

import edu.tamu.ctv.entity.Columntypestempl;
import edu.tamu.ctv.entity.Projecttypes;
import edu.tamu.ctv.repository.ColumnTypesTemplRepository;
import edu.tamu.ctv.repository.ProjectTypesRepository;

public class InitColumnTypeTemplCreateService
{
	public InitColumnTypeTemplCreateService(ColumnTypesTemplRepository columnTypesTemplRepository, ProjectTypesRepository projectTypesRepository)
	{
		createDefault(columnTypesTemplRepository, projectTypesRepository);			
	}

	private void createDefault(ColumnTypesTemplRepository columnTypesTemplRepository, ProjectTypesRepository projectTypesRepository)
	{
		List<Columntypestempl> list = new ArrayList<Columntypestempl>();
		
		Projecttypes projectType = projectTypesRepository.findOne(1l);
		if (0 == columnTypesTemplRepository.findByProjecttypeId(projectType.getId()).size())
		{
/*			int counter = 0;
			list.add(new Columntypestempl(null, projectType, "TYPE", "Type"));
			Columntypestempl element1 = new Columntypestempl(null, projectType, "TYPE", "Type");
			Columntypestempl element2 = new Columntypestempl(null, projectType, "TYPE", "Type");
			Columntypestempl element3 = new Columntypestempl(null, projectType, "TYPE", "Type");
			columnTypesTemplRepository.save();*/
		}
	}
}
