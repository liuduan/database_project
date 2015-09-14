package edu.tamu.ctv.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Columntypes implements java.io.Serializable
{
	private Long id;
	private Columntypes columntypes;
	private Projects projects;
	private String code;
	private String name;
	private String notes;
	private Date registereddt;
	private Set<Columnheaders> columnheaderses = new HashSet<Columnheaders>(0);
	private Set<Columntypes> columntypeses = new HashSet<Columntypes>(0);

	public Columntypes()
	{
	}

	public Columntypes(Long id, Columntypes columntypes, Projects projects, String code, String name, Date registereddt)
	{
		this.id = id;
		this.columntypes = columntypes;
		this.projects = projects;
		this.code = code;
		this.name = name;
		this.registereddt = registereddt;
	}

	public Columntypes(Long id, Columntypes columntypes, Projects projects, String code, String name, String notes, Date registereddt, Set<Columnheaders> columnheaderses, Set<Columntypes> columntypeses)
	{
		this.id = id;
		this.columntypes = columntypes;
		this.projects = projects;
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.registereddt = registereddt;
		this.columnheaderses = columnheaderses;
		this.columntypeses = columntypeses;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Columntypes getColumntypes()
	{
		return this.columntypes;
	}

	public void setColumntypes(Columntypes columntypes)
	{
		this.columntypes = columntypes;
	}

	public Projects getProjects()
	{
		return this.projects;
	}

	public void setProjects(Projects projects)
	{
		this.projects = projects;
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

	public String getNotes()
	{
		return this.notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public Date getRegistereddt()
	{
		return this.registereddt;
	}

	public void setRegistereddt(Date registereddt)
	{
		this.registereddt = registereddt;
	}

	public Set<Columnheaders> getColumnheaderses()
	{
		return columnheaderses;
	}

	public void setColumnheaderses(Set<Columnheaders> columnheaderses)
	{
		this.columnheaderses = columnheaderses;
	}

	public Set<Columntypes> getColumntypeses()
	{
		return columntypeses;
	}

	public void setColumntypeses(Set<Columntypes> columntypeses)
	{
		this.columntypeses = columntypeses;
	}


}
