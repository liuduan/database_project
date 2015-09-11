package edu.tamu.ctv.utils.importdata.toxpi;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileProcessor
{
	private String VALID_CSV_FILE = "Please select valid .csv file.";

	private FileData fileData;
	private String filePath;
	private Map<String, Set<Component>> typeComponentsMap;
	private Map<String, Set<Component>> sourceComponentsMap;
	List<Component> componentsList;

	public FileProcessor()
	{
		this.typeComponentsMap = new HashMap<String, Set<Component>>();
		this.sourceComponentsMap = new HashMap<String, Set<Component>>();
		this.componentsList = new ArrayList<Component>();
	}

	public void readFile(String absolutePath, Map<String, FileData> fileDataMap, String originalFileName) throws Exception
	{
		this.filePath = absolutePath;

		CSVReader reader = new CSVReader(new FileReader(absolutePath));
		try
		{
			int i = 0;
			String str[];
			int count = Utility.countFileLines(absolutePath);
			if (count <= 0) throw new IOException(VALID_CSV_FILE);

			List<Substance> chemicalsList = new ArrayList<Substance>();

			Map<Substance, String[]> chemicals = new HashMap<Substance, String[]>();
			String[] components = new String[1];
			String[] names = new String[1];
			String[] weights = new String[1];
			String[] sources = new String[1];
			String[] types = new String[1];
			Set<String> duplicatedComponents = new HashSet<String>();
			int substanceId = 0;

			while ((str = reader.readNext()) != null)
			{
				if (str.length < 4) throw new IOException("The file you're trying to read does not correspond to ToxPi file format. Please see manual for more information.");
				switch (i++)
				{
					case 0:
					{// first row - weights
						weights = Arrays.copyOfRange(str, 4, str.length);
						break;
					}
					case 1:
					{// second row - slice name
						names = Arrays.copyOfRange(str, 4, str.length);
						break;
					}
					case 2:
					{// third row - type
						types = Arrays.copyOfRange(str, 4, str.length);
						break;
					}
					case 3:
					{// fourth row - source
						sources = Arrays.copyOfRange(str, 4, str.length);
						break;
					}
					case 4:
					{// fourth row component
						components = Arrays.copyOfRange(str, 4, str.length);
						break;
					}
					default:
					{
						Substance substance = new Substance(substanceId++, Arrays.copyOfRange(str, 2, 3)[0], Arrays.copyOfRange(str, 3, 4)[0], Arrays.copyOfRange(str, 1, 2)[0]);

						if (chemicals.containsKey(substance))
						{
							if (!Arrays.equals(chemicals.get(substance), Arrays.copyOfRange(str, 4, str.length)))
								throw new IOException("Please make sure you don't have any duplicated chemicals in your data file!");
						}
						else
						{
							Map<String, Double> componentsMap = buildComponentsMap(components, Arrays.copyOfRange(str, 4, str.length));
							substance.setComponents(componentsMap);
							chemicalsList.add(substance);
						}
						Set<String> tempSet = checkDuplicatedComponentsInFiles(substance, fileDataMap, filePath);
						if (tempSet != null) duplicatedComponents.addAll(tempSet);

						break;
					}
				}

			}

			if (!duplicatedComponents.isEmpty())
			{
				if (duplicatedComponents.size() > 10) throw new IOException(duplicatedComponents.toString().substring(0, 400) + "...");
				else throw new IOException(duplicatedComponents.toString());
			}

			this.componentsList = buildComponentsListAndTypeSourceMaps(components, types, sources);

			if (chemicalsList.isEmpty()) throw new IOException("Please use the file with at least one chemical.");

			for (Substance substance : chemicalsList)
			{
				List<Slice> slicesList = buildSlices(componentsList, names, weights);
				substance.setSlices(slicesList);
			}

			fileData = new FileData(absolutePath, chemicalsList, this.componentsList, this.typeComponentsMap, this.sourceComponentsMap, originalFileName);

		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			reader.close();
		}
	}

	private Map<String, Double> buildComponentsMap(String[] names, String[] values) throws Exception
	{
		if (names.length != values.length) throw new Exception("Number of the componet names and values does not match. Please check your input file and try again!");
		Map<String, Double> result = new HashMap<String, Double>(names.length);
		for (int i = 0; i < names.length; i++)
		{
			String name = names[i];
			String value = values[i];
			Double val = new Double(0);
			try
			{
				if (value.isEmpty() || value.equalsIgnoreCase("na")) val = Double.NaN;
				else val = Double.parseDouble(value);
			}
			catch (NumberFormatException nex)
			{
				val = Double.NaN;
			}
			finally
			{
				if (result.containsKey(name))
				{
					if (!result.get(name).equals(val)) throw new Exception("Component with name '" + name + "' is duplicated in your file, but values don't match!");
				}
				result.put(name, val);
			}
		}
		return result;
	}

	public void writeFile(File f, Collection<Substance> substances, Map<String, FileData> fileDataList) throws Exception
	{
		CSVWriter writer = new CSVWriter(new FileWriter(f));
		ArrayList<String> component_names = new ArrayList<String>();

		try
		{
			int i = 0;
			int count = substances.size();
			double step = 100d / count;

			// get number of columns for future csv file
			int n = 0;
			Substance substance = (Substance) substances.toArray()[0];
			for (Iterator<Slice> it = substance.getSlices().iterator(); it.hasNext();)
			{
				n += it.next().getComponents().size();
			}
			// Creating csv header
			String[] weights = new String[n + 4];
			String[] names = new String[n + 4];
			String[] types = new String[n + 4];
			String[] sources = new String[n + 4];
			String[] components = new String[n + 4];
			String[] substance_line = new String[n + 4];

			weights[0] = weights[1] = weights[2] = weights[3] = names[0] = names[1] = names[2] = names[3] = types[0] = types[1] = types[2] = types[3] = sources[0] = sources[1] = sources[2] = sources[3] = "";
			components[0] = "row_order";
			components[1] = "chemical_source_sid";
			components[2] = "casrn";
			components[3] = "chemical_name";
			int k = 4;
			for (Iterator<Slice> it = substance.getSlices().iterator(); it.hasNext();)
			{
				Slice sl = it.next();
				for (Iterator<Component> j = sl.getComponents().iterator(); j.hasNext();)
				{
					weights[k] = String.valueOf(sl.getWeight());
					names[k] = sl.getName() + String.format("#%06X", (0xFFFFFF & sl.getColor().getRGB())) + "@" + sl.getScaling();
					Component cc = j.next();
					components[k] = cc.getName();
					types[k] = cc.getType();
					sources[k] = cc.getSource();
					component_names.add(cc.getName());
					k++;
				}
			}
			writer.writeNext(weights);
			writer.writeNext(names);
			writer.writeNext(types);
			writer.writeNext(sources);
			writer.writeNext(components);
			// finish with header

			// start filling substances data
			for (Iterator<Substance> it = substances.iterator(); it.hasNext();)
			{
				Substance sb = it.next();
				substance_line[0] = new Integer(i + 1).toString();
				substance_line[1] = sb.getSource_name_sid();
				substance_line[2] = sb.getCasrn();
				substance_line[3] = sb.getName();
				k = 4;
				Map<String, Double> componentsMap = sb.getComponents();
				for (String cName : component_names)
				{
					substance_line[k++] = String.valueOf(componentsMap.get(cName));
				}
				writer.writeNext(substance_line);
			}

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			writer.close();
		}
	}

	/**
	 * Method responsible for checking each component and it's value in all the
	 * selected files
	 * 
	 * @param chemical
	 * @param fileDataMap
	 * @param fileName
	 * @return null if no duplicates were found, a set of error messages if
	 *         duplicates were found
	 */
	private Set<String> checkDuplicatedComponentsInFiles(Substance chemical, Map<String, FileData> fileDataMap, String fileName)
	{
		Set<String> res = new HashSet<String>();
		// iterating through file names in fileData map
		for (String fileN : fileDataMap.keySet())
		{
			// if file contains current substance
			if (fileDataMap.get(fileN).getChemicals().contains(chemical))
			{
				Map<String, Double> components = chemical.getComponents();
				Map<String, Double> componentsToCompare = fileDataMap.get(fileN).getChemicals().get(fileDataMap.get(fileN).getChemicals().indexOf(chemical)).getComponents();
				// iterating through component names in the particular file
				for (String name : components.keySet())
					// iterating through components that has been just read
					for (String nameToCompare : componentsToCompare.keySet())
					{
						// if component names are equal
						if (name.equals(nameToCompare))
						{
							if (!components.get(name).equals(componentsToCompare.get(nameToCompare))) res.add(" Duplicated component " + name + " in chemical " + chemical + " value in file " + fileName + " = "
									+ components.get(name) + " in file " + fileN + " = " + componentsToCompare.get(nameToCompare) + System.getProperty("line.separator"));
						}
					}
			}
		}
		return res.isEmpty() ? null : res;
	}

	public FileData getFileData()
	{
		return fileData;
	}

	public Map<String, Set<Component>> getTypeComponentsMap()
	{
		return typeComponentsMap;
	}

	public void setTypeComponentsMap(Map<String, Set<Component>> typeComponentsMap)
	{
		this.typeComponentsMap = typeComponentsMap;
	}

	/**
	 * 
	 * @param components
	 * @param names
	 * @param weights
	 * @return list of Slice object to be used for recreating current data file
	 */
	private List<Slice> buildSlices(List<Component> components, String[] names, String[] weights) throws IOException
	{
		List<Slice> slices = new ArrayList<Slice>();
		Map<String, String> colors = new HashMap<String, String>();
		Map<String, String> scaling = new HashMap<String, String>();
		String[] plainNames = new String[names.length];
		for (int i = 0; i < names.length; i++)
		{
			if (names[i].contains("#") && names[i].contains("@"))
			{
				colors.put(names[i].substring(0, names[i].lastIndexOf("#")), names[i].substring(names[i].lastIndexOf("#") + 1, names[i].lastIndexOf("@")));
				scaling.put(names[i].substring(0, names[i].lastIndexOf("#")), names[i].substring(names[i].lastIndexOf("@") + 1));
				plainNames[i] = names[i].substring(0, names[i].lastIndexOf("#"));

			}
			else if (names[i].contains("#"))
			{
				colors.put(names[i].substring(0, names[i].lastIndexOf("#")), names[i].substring(names[i].lastIndexOf("#") + 1));
				plainNames[i] = names[i].substring(0, names[i].lastIndexOf("#"));
				scaling.put(names[i].substring(0, names[i].lastIndexOf("#")), ToxPiConfig.SCALING.LINEAR.toString());
			}
			else
			{
				colors.put(names[i], "");
				scaling.put(names[i], ToxPiConfig.SCALING.LINEAR.toString());
				plainNames[i] = names[i];

			}
		}

		List<String> listNames = Arrays.asList(plainNames);
		Set<String> uniqueNames = new LinkedHashSet<String>(listNames);
		// List<int[]> ranges = findSliceRanges(listNames,uniqueNames);
		List<String> uniqueTypes = new ArrayList<String>();
		List<String> uniqueWeights = new ArrayList<String>();
		List<List<Component>> uniqueComponents = new ArrayList<List<Component>>();
		List<Integer> positions = new ArrayList<Integer>();
		double step = 100d / uniqueNames.size() * 3;

		int _progress = 0;

		// getting types for unique slices based on position
		for (String name : uniqueNames)
		{
			int pos = listNames.indexOf(name);
			positions.add(pos);
			uniqueTypes.add(components.get(pos).getType());
			uniqueWeights.add(weights[pos]);
		}

		int k = 0;
		for (int i = 1; i < uniqueNames.size(); i++)
		{
			uniqueComponents.add(components.subList(k, positions.get(i)));
			k = positions.get(i);
		}
		uniqueComponents.add(components.subList(k, names.length));

		// creating slices for recreation of the file data
		for (int i = 0; i < uniqueNames.toArray().length; i++)
		{

			String type = uniqueTypes.get(i);

			String color = colors.get((String) uniqueNames.toArray()[i]);
			String particularScaling = scaling.get((String) uniqueNames.toArray()[i]);

			if (!color.isEmpty())
			{
				try
				{
					// TODO Implement check Color
					int intValue = Integer.parseInt(color, 16);
					// Color c = new Color(intValue);
				}
				catch (Exception ex)
				{
					color = "";
				}
			}

			if (color.isEmpty())
			{
				String[] strColor = ToxPiConfig.COLORS.get(type.trim().toLowerCase());
				if (strColor == null)
				{
					strColor = ToxPiConfig.COLORS.get(ToxPiConfig.CUSTOM);
				}
				if (i > 24) color = "FFF";
				else color = strColor[i];
			}

			ToxPiConfig.SCALING scal = ToxPiConfig.SCALING.getType(particularScaling);

			Slice s = new Slice(i, (String) uniqueNames.toArray()[i], type, uniqueComponents.get(i), uniqueWeights.get(i), color, scal);

			slices.add(s);
		}

		return slices;
	}

	/**
	 * method responsible for filling componentsList, typeComponentsMap and
	 * sourceComponentsMap
	 * 
	 * @param components
	 *            - array of component names
	 * @param types
	 *            - array of type names
	 * @param sources
	 *            - array of source names
	 */
	private List<Component> buildComponentsListAndTypeSourceMaps(String[] components, String[] types, String[] sources)
	{
		List<Component> res = new ArrayList<Component>();
		double step = 100d / components.length;
		// filling componentsList container
		for (int k = 0; k < components.length; k++)
		{
			Component c = new Component(components[k], types[k], sources[k]);
			res.add(c);
			if (this.typeComponentsMap.containsKey(types[k]))
			{
				this.typeComponentsMap.get(types[k]).add(c);
			}
			else
			{
				Set<Component> tempMap = new HashSet<Component>();
				tempMap.add(c);
				this.typeComponentsMap.put(types[k], tempMap);
			}
			if (this.sourceComponentsMap.containsKey(sources[k]))
			{
				this.sourceComponentsMap.get(sources[k]).add(c);
			}
			else
			{
				Set<Component> tempMap = new HashSet<Component>();
				tempMap.add(c);
				this.sourceComponentsMap.put(sources[k], tempMap);
			}
		}
		return res;
	}

	public void writeChartDataFile(File f, Collection<Substance> substances, Map<String, FileData> fileDataList) throws IOException, Exception
	{

		CSVWriter writer = new CSVWriter(new FileWriter(f));

		try
		{
			int count = substances.size();
			double step = 100d / count;

			// Creating csv header
			String[] header = new String[] { "row_order", "chemical_source_sid", "casrn", "chemical_name", "ToxPi value", "Lower 95%", "Upper 95%", "Rank", "Lower rank 95%", "Upper rank 95%",
					"Number of components used", "Number of slices" };

			writer.writeNext(header);

			String[] substanceInfo = new String[header.length];
			int i = 0;
			Object[] sortedSubstances = substances.toArray();
			Arrays.sort(sortedSubstances);
			List<Object> temp = Arrays.asList(sortedSubstances);
			Collections.reverse(temp);
			for (Object object : temp)
			{
				Substance substance = (Substance) object;
				substanceInfo[0] = String.valueOf(i + 1);
				substanceInfo[1] = substance.getSource_name_sid();
				substanceInfo[2] = substance.getCasrn();
				substanceInfo[3] = substance.getName();
				substanceInfo[4] = substance.getTox_pi_score().toString();
				if (!ToxPiConfig.SKIP_BOOTSTRAPPING)
				{
					substanceInfo[5] = substance.get95range()[0].toString();
					substanceInfo[6] = substance.get95range()[1].toString();
				}
				else substanceInfo[5] = substanceInfo[6] = "No bootstrapping was used";

				substanceInfo[7] = String.valueOf(substance.getRank());

				if (!ToxPiConfig.SKIP_BOOTSTRAPPING)
				{
					substanceInfo[8] = String.valueOf(substance.getRank_range()[0]);
					substanceInfo[9] = String.valueOf(substance.getRank_range()[1]);
				}
				else substanceInfo[8] = substanceInfo[9] = "No bootstrapping was used";

				substanceInfo[10] = String.valueOf(substance.getComponents().size());
				substanceInfo[11] = String.valueOf(substance.getSlices().size());
				writer.writeNext(substanceInfo);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			writer.close();
		}
	}
}
