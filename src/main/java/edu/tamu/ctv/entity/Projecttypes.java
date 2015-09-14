package edu.tamu.ctv.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Projecttypes implements java.io.Serializable
{
	private Long id;
	private String code;
	private String name;
	private String notes;
	private Date registereddt;
	private Date lastvisitdt;
	private Set<Projects> projectses = new HashSet<Projects>(0);
	
	private Set<Rowtypestempl> rowtypestempl = new HashSet<Rowtypestempl>(0);
	private Set<Columntypestempl> columntypestempl = new HashSet<Columntypestempl>(0);

	public Projecttypes()
	{
	}

	public Projecttypes(Long id, String code, String name, Date registereddt, Date lastvisitdt)
	{
		this.id = id;
		this.code = code;
		this.name = name;
		this.registereddt = registereddt;
		this.lastvisitdt = lastvisitdt;
	}

	public Projecttypes(Long id, String code, String name, String notes, Date registereddt, Date lastvisitdt, Set<Projects> projectses)
	{
		this.id = id;
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.registereddt = registereddt;
		this.lastvisitdt = lastvisitdt;
		this.projectses = projectses;
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

	public Date getLastvisitdt()
	{
		return this.lastvisitdt;
	}

	public void setLastvisitdt(Date lastvisitdt)
	{
		this.lastvisitdt = lastvisitdt;
	}

	public Set<Projects> getProjectses()
	{
		return this.projectses;
	}

	public void setProjectses(Set<Projects> projectses)
	{
		this.projectses = projectses;
	}

	public Set<Rowtypestempl> getRowtypestempl()
	{
		return rowtypestempl;
	}

	public void setRowtypestempl(Set<Rowtypestempl> rowtypestempl)
	{
		this.rowtypestempl = rowtypestempl;
	}

	public Set<Columntypestempl> getColumntypestempl()
	{
		return columntypestempl;
	}

	public void setColumntypestempl(Set<Columntypestempl> columntypestempl)
	{
		this.columntypestempl = columntypestempl;
	}
}