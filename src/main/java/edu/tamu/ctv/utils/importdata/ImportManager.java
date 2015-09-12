package edu.tamu.ctv.utils.importdata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.ctv.entity.*;
import edu.tamu.ctv.repository.*;
import edu.tamu.ctv.utils.Auth;
import edu.tamu.ctv.utils.DateUtil;
import edu.tamu.ctv.utils.importdata.toxpi.DataTransformation;

public class ImportManager implements Runnable
{
	private final Logger logger = LoggerFactory.getLogger(ImportManager.class);
	
	@Autowired
	private ProjectsRepository projectRepository;
	@Autowired
	private RowTypesRepository rowTypesRepository;
	@Autowired
	private ColumnTypesRepository columnTypesRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private RowHeadersRepository rowHeaderRepository;
	@Autowired
	private ColumnHeadersRepository columnHeaderRepository;
	@Autowired
	private ComponentsRepository componentsRepository;
	@Autowired
	private ResultsRepository resultsRepository;
	
	
	private String fileLocation = "";
	//TODO: Change
	private Long projectId = 1l;
	
	public ImportManager()
	{}
	
	public ImportManager(String fileLocation)
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
				List<Rowtypes> rowTypes = rowTypesRepository.findByProjectsCode(currentProject.getCode());
				List<Columntypes> columnTypes = columnTypesRepository.findByProjectsCode(currentProject.getCode());
				
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
						orderId = ordersRepository.getNextOrderId();
						
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
								currentRowHeader = rowHeaderRepository.findByCodeAndRowTypesProjectsCode(rowCode, currentProject.getCode());
							}
							if (null == currentRowHeader)
							{
								currentRowHeader = new Rowheaders(null, rowTypes.get(rowCounter), rowCode, rowCode, DateUtil.GetCurrentDate(), DateUtil.GetCurrentDate());
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
							currentColumnHeader = columnHeaderRepository.findByCodeAndHeaderTypesProjectsCode(columnCode, currentProject.getCode());
						}
						if (null == currentColumnHeader)
						{
							currentColumnHeader = new Columnheaders(null, parentColumnHeader, columnTypes.get(columnLevel), level.getCode(), level.getCode(), DateUtil.GetCurrentDate(), DateUtil.GetCurrentDate());
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
						currentComponent = new Components(null, currentProject, Auth.getDefaultUnit(), Auth.getCurrentUser(), component.getCode(), component.getCode(), DateUtil.GetCurrentDate());
						currentComponent.setColumnheaders(parentColumnHeader);
						componentList.add(currentComponent);
					}
					
					Results currentResult = new Results(null, currentComponent, currentProject, Auth.getCurrentUser(), orderId, DateUtil.GetCurrentDate());
					currentResult.setStrresult(result.getValue());
					resultList.add(currentResult);
					

				}
				
				
				//Save to DB
				for (Rowheaders header : rowHeaderList)
				{
					rowHeaderRepository.save(header);
				}
				for (Columnheaders header : columnHeaderList)
				{
					columnHeaderRepository.save(header);
				}
				for (Components component : componentList)
				{
					componentsRepository.save(component);
				}
				for (Results result : resultList)
				{
					resultsRepository.save(result);
				}
				for (Orders order : orderList)
				{
					ordersRepository.save(order);
				}

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
