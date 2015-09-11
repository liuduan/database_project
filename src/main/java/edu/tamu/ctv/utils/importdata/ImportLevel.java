package edu.tamu.ctv.utils.importdata;

public class ImportLevel
{
	private ImportLevel childLevel;
	private String code;
	
	public ImportLevel()
	{
	}
	
	public ImportLevel(ImportLevel childLevel, String code)
	{
		this.childLevel = childLevel;
		this.code = code;
	}

	public ImportLevel getChildLevel()
	{
		return childLevel;
	}

	public void setChildLevel(ImportLevel childLevel)
	{
		this.childLevel = childLevel;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
}
