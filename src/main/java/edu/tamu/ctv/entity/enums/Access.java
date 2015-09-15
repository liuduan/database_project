package edu.tamu.ctv.entity.enums;

public enum Access
{
	PRIVATE(0, "Private"),
	PROTECTED(1, "Protected"),
	REGISTEREDREAD(2, "For registered users (readonly)"),
	REGISTEREDEDIT(3, "For registered users (allow edit)"),
	PUBLICREAD(4, "Public (readonly)"),
	PUBLICEDIT(5, "Public (allow edit)");

	private int id;
	private String value;

	Access(int id, String value)
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
	
	public static Access parse(String val)
	{
		Access access = null;
		for (Access item : Access.values())
		{
			if (item.getValue() == val)
			{
				access = item;
				break;
			}
		}
		return access;
	}
	
	public static Access parse(int id)
	{
		Access access = null;
		for (Access item : Access.values())
		{
			if (item.getId() == id)
			{
				access = item;
				break;
			}
		}
		return access;
	}
}
