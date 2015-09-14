package edu.tamu.ctv.entity;

public class Rowtypestempl implements java.io.Serializable
{

	private Long id;
	private String code;
	private String name;
	private int showorder;
	private Projecttypes projecttype;
	
	public Rowtypestempl()
	{
	}

	public Rowtypestempl(Long id, Projecttypes projecttype, String code, String name, int showorder)
	{
		this.id = id;
		this.code = code;
		this.name = name;
		this.showorder = showorder;
		this.projecttype = projecttype;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return this.code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getShoworder()
	{
		return this.showorder;
	}

	public void setShoworder(int showorder)
	{
		this.showorder = showorder;
	}

	public Projecttypes getProjecttype()
	{
		return projecttype;
	}

	public void setProjecttype(Projecttypes projecttype)
	{
		this.projecttype = projecttype;
	}
}
