package edu.tamu.ctv.entity.customdefined;

import java.util.Map;

import edu.tamu.ctv.entity.Columnheaders;
import edu.tamu.ctv.entity.Components;

import java.util.ArrayList;
import java.util.List;

public class Analysis
{
	public Map<String, String>[] results;	
	public String[] columns;
	public ArrayList<ArrayList<Columnheaders>> columnheaders;	
	public List<Components> components;
	
	public Analysis()
	{}
	
	public Analysis( Map<String, String>[] results, String[] columns, ArrayList<ArrayList<Columnheaders>> columnheaders, List<Components> components)
	{
		this.results = results;
		this.columns = columns;
		this.columnheaders = columnheaders;
		this.components = components;
	}

	public Map<String, String>[] getResults()
	{
		return results;
	}

	public void setResults(Map<String, String>[] results)
	{
		this.results = results;
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
}