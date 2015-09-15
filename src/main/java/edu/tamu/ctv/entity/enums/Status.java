package edu.tamu.ctv.entity.enums;

public enum Status
{
	NEW(0, "New"),
	INPROCESS(1, "In process"),
	FINISHED(2, "Finished");

	private int id;
	private String value;

	Status(int id, String value)
	{
		this.id = id;
		this.value = value;
	}

	public int getId()
	{
		return id;
	}
	
	public String getValue()
	{
		return value;
	}

	public static Status parse(String val)
	{
		Status status = null;
		for (Status item : Status.values())
		{
			if (item.getValue() == val)
			{
				status = item;
				break;
			}
		}
		return status;
	}
	
	public static Status parse(int id)
	{
		Status status = null;
		for (Status item : Status.values())
		{
			if (item.getId() == id)
			{
				status = item;
				break;
			}
		}
		return status;
	}
}
