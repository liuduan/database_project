package edu.tamu.ctv.entity;
// Generated Sep 5, 2015 1:43:34 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Rowtypes generated by hbm2java
 */
public class Rowtypes implements java.io.Serializable
{

	private Long id;
	private Projects projects;
	private String code;
	private String name;
	private int showorder;
	private String notes;
	private Date registereddt;
	private Date lastvisitdt;
	private Set rowheaderses = new HashSet(0);

	public Rowtypes()
	{
	}

	public Rowtypes(Long id, Projects projects, String code, String name, int showorder, Date registereddt, Date lastvisitdt)
	{
		this.id = id;
		this.projects = projects;
		this.code = code;
		this.name = name;
		this.showorder = showorder;
		this.registereddt = registereddt;
		this.lastvisitdt = lastvisitdt;
	}

	public Rowtypes(Long id, Projects projects, String code, String name, int showorder, String notes, Date registereddt, Date lastvisitdt, Set rowheaderses)
	{
		this.id = id;
		this.projects = projects;
		this.code = code;
		this.name = name;
		this.showorder = showorder;
		this.notes = notes;
		this.registereddt = registereddt;
		this.lastvisitdt = lastvisitdt;
		this.rowheaderses = rowheaderses;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
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
	
	public int getShoworder()
	{
		return this.showorder;
	}

	public void setShoworder(int id)
	{
		this.showorder = showorder;
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

	public Set getRowheaderses()
	{
		return this.rowheaderses;
	}

	public void setRowheaderses(Set rowheaderses)
	{
		this.rowheaderses = rowheaderses;
	}

}
