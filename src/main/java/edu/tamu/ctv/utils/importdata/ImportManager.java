package edu.tamu.ctv.utils.importdata;

import java.util.ArrayList;
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
	private UnitsRepository unitsRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private SequencesRepository sequencesRepository;
	
	
	private String fileLocation = "";
	//TODO: Change
	private Long projectId = 1l;
	
	public ImportManager()
	{}
	
	public void setFile(String fileLocation)
	{
		this.fileLocation = fileLocation;
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
	
	private void storeDataToDataBase(UniversalDataImport data)
	{
		try
		{
			if (data != null)
			{
				Map<String, Long> orderMap = new LinkedHashMap<String, Long>();
				
				Projects currentProject = projectRepository.findOne(projectId);
				
				List<Rowtypes> rowTypes = new ArrayList<Rowtypes>(currentProject.getRowtypeses());
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
								if (rowCode.equals(rh.getCode()))
								{
									currentRowHeader = rh;
									continue;
								}
							}
							if (null == currentRowHeader)
							{
								List<Rowheaders> list = rowHeaderRepository.findByCodeAndRowTypesProjectsCode(rowCode, currentProject.getCode());
								if (list.size() > 0)
								{
									currentRowHeader = list.get(0);
								}
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
								continue;
							}
						}
						if (null == currentColumnHeader)
						{
							List<Columnheaders> list = columnHeaderRepository.findByCodeAndHeaderTypesProjectsCode(columnCode, currentProject.getCode());
							if (list.size() > 0)
							{
								currentColumnHeader = list.get(0);
							}
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
							continue;
						}
					}
					if (null == currentComponent)
					{
						currentComponent = componentsRepository.findByCodeAndProjectsCode(component.getCode(), currentProject.getCode());
					}
					if (null == currentComponent)
					{
						currentComponent = new Components(null, currentProject, unitsRepository.findOne(1l), usersRepository.findOne(1l), component.getCode(), component.getCode(), DateUtil.GetCurrentDate());
						currentComponent.setColumnheaders(parentColumnHeader);
						componentList.add(currentComponent);
					}
					
					Results currentResult = new Results(null, currentComponent, currentProject, usersRepository.findOne(1l), orderId, DateUtil.GetCurrentDate());
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
