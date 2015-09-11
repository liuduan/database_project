package edu.tamu.ctv.utils.importdata;

import java.util.ArrayList;
import java.util.List;

public class ImportResult
{
	private String value;
	private ImportComponent component;
	private List<String> rows = new ArrayList<String>();
	
	public ImportResult()
	{
	}
	
	public ImportResult(String value, ImportComponent component, List<String> rows)
	{
		this.value = value;
		this.component = component;
		this.rows = rows;
	}
	
	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	
	public ImportComponent getComponent()
	{
		return component;
	}

	public void setComponent(ImportComponent component)
	{
		this.component = component;
	}

	public List<String> getRows()
	{
		return rows;
	}

	public void setRows(List<String> rows)
	{
		this.rows = rows;
	}
	
	public String getOrderKey()
	{
		String result = "";
		for (String str : rows)
		{
			result += "-" + str + "-";
		}
		return result;
	}
}
