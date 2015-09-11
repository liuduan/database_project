package edu.tamu.ctv.utils.importdata;

import java.util.ArrayList;
import java.util.List;

public class UniversalDataImport
{
	private List<ImportResult> results  = new ArrayList<ImportResult>();
	
	public UniversalDataImport()
	{
	}
	
	public List<ImportResult> getResults()
	{
		return results;
	}

	public void setResults(List<ImportResult> results)
	{
		this.results = results;
	}

	public UniversalDataImport(List<ImportResult> results)
	{
		this.results = results;
	}

}
