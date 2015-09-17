package edu.tamu.ctv.entity.customdefined;

import java.util.Map;

import edu.tamu.ctv.entity.Columnheaders;
import edu.tamu.ctv.entity.Columntypes;
import edu.tamu.ctv.entity.Components;
import edu.tamu.ctv.entity.Rowtypes;

import java.util.ArrayList;
import java.util.List;

public class Analysis
{
	private Map<String, String>[] results;	
	private Map<String, String>[] columnHeaderResults;
	private String[] columns;
	private ArrayList<ArrayList<Columnheaders>> columnheaders;	
	private List<Components> components;
	private List<Rowtypes> rowTypes;
	private List<Columntypes> columnTypes;
	
	public Analysis()
	{}
	
	public Analysis( Map<String, String>[] results, String[] columns, ArrayList<ArrayList<Columnheaders>> columnheaders, List<Components> components, List<Rowtypes> rowTypes, Map<String, String>[] columnHeaderResults, List<Columntypes> columnTypes)
	{
		this.results = results;
		this.columnHeaderResults = columnHeaderResults;
		this.columns = columns;
		this.columnheaders = columnheaders;
		this.components = components;
		this.rowTypes = rowTypes;
		this.columnTypes = columnTypes;
	}

	public Map<String, String>[] getResults()
	{
		return results;
	}

	public void setResults(Map<String, String>[] results)
	{
		this.results = results;
	}	
	
	public Map<String, String>[] getColumnHeaderResults()
	{
		return columnHeaderResults;
	}

	public void setColumnHeaderResults(Map<String, String>[] columnHeaderResults)
	{
		this.columnHeaderResults = columnHeaderResults;
	}	
	
	public String[] getColumns()
	{
		return columns;
	}

	public void setColumns(String[] columns)
	{
		this.columns = columns;
	}	
	public ArrayList<ArrayList<Columnheaders>> getColumnHeaders()
	{
		return columnheaders;
	}

	public void setColumnHeaders(ArrayList<ArrayList<Columnheaders>> columnheaders)
	{
		this.columnheaders = columnheaders;
	}
	public List<Components> getComponents()
	{
		return components;
	}

	public void setComponents(List<Components> components)
	{
		this.components = components;
	}
	public List<Rowtypes> getRowTypes()
	{
		return rowTypes;
	}

	public void setRowTypes(List<Rowtypes> rowTypes)
	{
		this.rowTypes = rowTypes;
	}
	
	public List<Columntypes> getColumnTypes()
	{
		return columnTypes;
	}

	public void setColumnTypes(List<Columntypes> columnTypes)
	{
		this.columnTypes = columnTypes;
	}
}