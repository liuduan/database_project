package edu.tamu.ctv.entity.customdefined;

import java.util.Map;

public class Analysis
{
	public Map<String, String>[] results;	
	public String[] columns;	
	
	public Analysis()
	{}
	
	public Analysis( Map<String, String>[] results, String[] columns)
	{
		this.results = results;
		this.columns = columns;	
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
}
