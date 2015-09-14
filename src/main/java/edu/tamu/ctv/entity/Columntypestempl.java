package edu.tamu.ctv.entity;

import java.util.HashSet;
import java.util.Set;


public class Columntypestempl implements java.io.Serializable
{
	private Long id;
	private Columntypestempl columntypes;
	private Projecttypes projecttype;
	private String code;
	private String name;
	private Set<Columntypestempl> columntypeses = new HashSet<Columntypestempl>(0);

	public Columntypestempl()
	{
	}

	public Columntypestempl(Long id, Projecttypes projecttype, Columntypestempl columntypes, String code, String name)
	{
		this.id = id;
		this.columntypes = columntypes;
		this.code = code;
		this.name = name;
		this.projecttype = projecttype;
	}

	public Columntypestempl(Long id, Projecttypes projecttype, Columntypestempl columntypes, String code, String name, Set<Columntypestempl> columntypeses)
	{
		this.id = id;
		this.columntypes = columntypes;
		this.code = code;
		this.name = name;
		this.columntypeses = columntypeses;
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
	
	public Columntypestempl getColumntypes()
	{
		return this.columntypes;
	}

	public void setColumntypes(Columntypestempl columntypes)
	{
		this.columntypes = columntypes;
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

	public Set<Columntypestempl> getColumntypeses()
	{
		return this.columntypeses;
	}

	public void setColumntypeses(Set<Columntypestempl> columntypeses)
	{
		this.columntypeses = columntypeses;
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
