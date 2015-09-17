package edu.tamu.ctv.utils.importdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.ctv.entity.*;
import edu.tamu.ctv.repository.*;
import edu.tamu.ctv.utils.DateUtil;
import edu.tamu.ctv.utils.importdata.toxpi.DataTransformation;
import edu.tamu.ctv.utils.session.ProjectAuthentication;

@Service("importManager")
public class ImportManager implements Runnable
{
	private final Logger logger = LoggerFactory.getLogger(ImportManager.class);

    @Autowired
    private ComponentsRepository componentsRepository;
	@Autowired
	private ProjectsRepository projectRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private RowHeadersRepository rowHeaderRepository;
	@Autowired
	private ColumnHeadersRepository columnHeaderRepository;
	@Autowired
	private ResultsRepository resultsRepository;
	@Autowired
	private SequencesRepository sequencesRepository;
	@Autowired
	private ProjectAuthentication projectAuthentication;
	
	private String fileLocation = "";
	//TODO: Change
	private Long projectId = 0l;
	
	List<Rowheaders> rowHeaderList = null;
	List<Columnheaders> columnHeaderList = null;
	List<Components> componentList = null;
	
	public ImportManager()
	{}
	
	public void setFile(String fileLocation)
	{
		this.fileLocation = fileLocation;
	}
	
	public void setProject(Long projectId)
	{
		this.projectId = projectId;
	}
	
	public UniversalDataImport parseToxPiFormat()
	{
		UniversalDataImport result = new UniversalDataImport();
		try
		{
			DataTransformation dt = new DataTransformation(fileLocation, fileLocation);
			result = dt.Transform();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	private Rowheaders findRowheaders(String rowCode, Long rowTypeId)
	{
		for (Rowheaders rowHeader : rowHeaderList)
		{
			if (rowHeader.getCode().equals(rowCode) && rowHeader.getRowtypes().getId().equals(rowTypeId))
			{
				return rowHeader;
			}
		}
		return null;
	}
	
	private Columnheaders findColumnheaders(String columnCode)
	{
		for (Columnheaders columnHeader : columnHeaderList)
		{
			if (columnHeader.getCode().equals(columnCode))
			{
				return columnHeader;
			}
		}
		return null;
	}
	
	private Components findComponents(String componentCode)
	{
		for (Components component : componentList)
		{
			if (component.getCode().equals(componentCode))
			{
				return component;
			}
		}
		return null;
	}
	
	private void storeDataToDataBase(UniversalDataImport data)
	{
		try
		{
			if (columnHeaderList != null) columnHeaderList.clear();
			if (rowHeaderList != null) rowHeaderList.clear();
			if (componentList != null) componentList.clear();
			
			if (data != null)
			{
				Projects currentProject = projectRepository.findOne(projectId);
				
				//TODO: throw Exception
				if (currentProject == null) return;

				Users currentUser = projectAuthentication.getCurrentUser();
				Units currentUnit = projectAuthentication.getDefaultUnit();
				
				columnHeaderList = columnHeaderRepository.findByColumntypesProjectsId(projectId);
				rowHeaderList = rowHeaderRepository.findByRowtypesProjectsId(projectId);
				componentList = componentsRepository.findByProjectsId(projectId);			
				Map<String, Long> orderMap = new LinkedHashMap<String, Long>();
				
				List<Rowtypes> rowTypes = new ArrayList<Rowtypes>(currentProject.getRowtypeses());
				Collections.sort(rowTypes, new Comparator<Rowtypes>()
				{
					public int compare(Rowtypes o1, Rowtypes o2)
					{
						return o1.getShoworder() - o2.getShoworder();
					}
				});
				
				List<Columntypes> columnTypes = new ArrayList<Columntypes>(currentProject.getColumntypeses());

				List<Rowheaders> rowHeaderList = new ArrayList<Rowheaders>();
				List<Columnheaders> columnHeaderList = new ArrayList<Columnheaders>();
				List<Components> componentList = new ArrayList<Components>();
				List<Results> resultList = new ArrayList<Results>();
				List<Orders> orderList = new ArrayList<Orders>();
				
				for (ImportResult result : data.getResults())
				{
					Long orderId = -1l;
					if (orderMap.containsKey(result.getOrderKey()))
					{
						orderId = orderMap.get(result.getOrderKey());
					}
					else
					{
						//TODO: !!! CHANGE !!!
						orderId = sequencesRepository.save(new Sequences()).getId();
						
						List<Rowheaders> rowHeaders = new ArrayList<Rowheaders>();
						for (int rowCounter = 0; rowCounter < rowTypes.size(); rowCounter++)
						{
							Rowheaders currentRowHeader = null;
							String rowCode = result.getRows().get(rowCounter);

							for (Rowheaders rh : rowHeaderList)
							{
								if (rowCode.equals(rh.getCode()) && rh.getRowtypes().getId().equals(rowTypes.get(rowCounter).getId()))
								{
									currentRowHeader = rh;
									continue;
								}
							}
							if (null == currentRowHeader)
							{
								currentRowHeader = findRowheaders(rowCode, rowTypes.get(rowCounter).getId());
							}
							if (null == currentRowHeader)
							{
								currentRowHeader = new Rowheaders(null, rowTypes.get(rowCounter), rowCode, rowCode, DateUtil.GetCurrentDate());
								rowHeaderList.add(currentRowHeader);
							}
							rowHeaders.add(currentRowHeader);
						}
						
						for (Rowheaders rowHeader : rowHeaders)
						{
							orderList.add(new Orders(null, rowHeader, orderId));
						}
						
						orderMap.put(result.getOrderKey(), orderId);
					}
					
					ImportComponent component = result.getComponent();

					ImportLevel level = component.getLevel();
					Columnheaders parentColumnHeader = null;
					int columnLevel = 0;
					while (level != null)
					{
						Columnheaders currentColumnHeader = null;
						String columnCode = level.getCode();
						
						for (Columnheaders ch : columnHeaderList)
						{
							if (columnCode.equals(ch.getCode()))
							{
								currentColumnHeader = ch;
								break;
							}
						}
						if (null == currentColumnHeader)
						{
							currentColumnHeader = findColumnheaders(columnCode);
						}
						if (null == currentColumnHeader)
						{
							currentColumnHeader = new Columnheaders(null, parentColumnHeader, columnTypes.get(columnLevel), level.getCode(), level.getCode(), DateUtil.GetCurrentDate());
							columnHeaderList.add(currentColumnHeader);
						}
						parentColumnHeader = currentColumnHeader;

						level = level.getChildLevel();
						columnLevel++;
					}
					
					Components currentComponent = null;			
					for (Components c : componentList)
					{
						if (component.getCode().equals(c.getCode()))
						{
							currentComponent = c;
							break;
						}
					}
					if (null == currentComponent)
					{
						currentComponent = findComponents(component.getCode());
					}
					if (null == currentComponent)
					{
						currentComponent = new Components(null, currentProject, currentUnit, currentUser, component.getCode(), component.getCode(), DateUtil.GetCurrentDate());
						currentComponent.setColumnheaders(parentColumnHeader);
						componentList.add(currentComponent);
					}
					
					Results currentResult = new Results(null, currentComponent, currentProject, currentUser, orderId, DateUtil.GetCurrentDate());
					currentResult.setStrresult(result.getValue());
					resultList.add(currentResult);
				}

				//Save to DB
				rowHeaderRepository.save(rowHeaderList);
				columnHeaderRepository.save(columnHeaderList);
				componentsRepository.save(componentList);
				resultsRepository.save(resultList);
				ordersRepository.save(orderList);
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	public void run()
	{
		storeDataToDataBase(parseToxPiFormat());
	}
}
