package edu.tamu.ctv.utils.importdata;

public class ImportComponent
{
	private String code;
	private ImportLevel level = null;
	
	public ImportComponent()
	{
	}
	
	public ImportComponent(String code, ImportLevel level)
	{
		this.code = code;
		this.level = level;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public ImportLevel getLevel()
	{
		return level;
	}

	public void setLevel(ImportLevel level)
	{
		this.level = level;
	}
}
