package edu.tamu.ctv.entity.customdefined;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnalysisResults
{
	private List<String> columnCodeList = new ArrayList<String>();
	private List<Map<String, String>> resultValueList = new ArrayList<Map<String, String>>();
	
	public AnalysisResults()
	{
	}
	
	public AnalysisResults(List<String> columnCodeList, List<Map<String, String>> resultValueList)
	{
		this.columnCodeList = columnCodeList;
		this.resultValueList = resultValueList;
	}
	
	public List<String> getColumnCodeList()
	{
		return columnCodeList;
	}
	public void setColumnCodeList(List<String> columnCodeList)
	{
		this.columnCodeList = columnCodeList;
	}
	public List<Map<String, String>> getResultValueList()
	{
		return resultValueList;
	}
	public void setResultValueList(List<Map<String, String>> resultValueList)
	{
		this.resultValueList = resultValueList;
	}
}
