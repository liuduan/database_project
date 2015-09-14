package edu.tamu.ctv.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Projects implements java.io.Serializable
{
	private Long id;
	private Projecttypes projecttypes;
	private Users users;
	private String code;
	private String name;
	private int access;
	private String notes;
	private Date starts;
	private Date ends;
	private Long status;
	private Date registereddt;
	private Date lastvisitdt;
	
	private Set<Users> projectmanagerses = new HashSet<Users>(0);
	private Set<Users> projectreviewerses = new HashSet<Users>(0);
	private Set<Users> projectmemberses = new HashSet<Users>(0);
	
	private Set<Rowtypes> rowtypeses = new HashSet<Rowtypes>(0);
	private Set<Components> componentses = new HashSet<Components>(0);
	private Set<Columntypes> columntypeses = new HashSet<Columntypes>(0);
	private Set<Results> resultses = new HashSet<Results>(0);

	public Projects()
	{
	}

	public Projects(Long id, Projecttypes projecttypes, Users users, String code, String name, int access, Date registereddt, Date lastvisitdt)
	{
		this.id = id;
		this.projecttypes = projecttypes;
		this.users = users;
		this.code = code;
		this.name = name;
		this.access = access;
		this.registereddt = registereddt;
		this.lastvisitdt = lastvisitdt;
	}

	public Projects(Long id, Projecttypes projecttypes, Users users, String code, String name, int access, String notes, Date registereddt, Date lastvisitdt, Set<Rowtypes> rowtypeses,
			Set<Components> componentses, Set<Columntypes> columntypeses, Set<Results> resultses)
	{
		this.id = id;
		this.projecttypes = projecttypes;
		this.users = users;
		this.code = code;
		this.name = name;
		this.access = access;
		this.notes = notes;
		this.registereddt = registereddt;
		this.lastvisitdt = lastvisitdt;
		this.rowtypeses = rowtypeses;
		this.componentses = componentses;
		this.columntypeses = columntypeses;
		this.resultses = resultses;
		this.resultses = resultses;
	}
	
	public boolean isNew()
	{
		return null == id;
	}
	
	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Projecttypes getProjecttypes()
	{
		return this.projecttypes;
	}

	public void setProjecttypes(Projecttypes projecttypes)
	{
		this.projecttypes = projecttypes;
	}

	public Users getUsers()
	{
		return this.users;
	}

	public void setUsers(Users users)
	{
		this.users = users;
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

	public int getAccess()
	{
		return this.access;
	}

	public void setAccess(int access)
	{
		this.access = access;
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

	public Set<Users> getProjectmanagerses()
	{
		return this.projectmanagerses;
	}

	public void setProjectmanagerses(Set<Users> projectmanagerses)
	{
		this.projectmanagerses = projectmanagerses;
	}
	
	public Set<Users> getProjectreviewerses()
	{
		return this.projectreviewerses;
	}

	public void setProjectreviewerses(Set<Users> projectreviewerses)
	{
		this.projectreviewerses = projectreviewerses;
	}
	
	public Set<Users> getProjectmemberses()
	{
		return this.projectmemberses;
	}

	public void setProjectmemberses(Set<Users> projectmemberses)
	{
		this.projectmemberses = projectmemberses;
	}
	
	public Date getStarts()
	{
		return starts;
	}

	public void setStarts(Date starts)
	{
		this.starts = starts;
	}

	public Date getEnds()
	{
		return ends;
	}

	public void setEnds(Date ends)
	{
		this.ends = ends;
	}

	public Long getStatus()
	{
		return status;
	}

	public void setStatus(Long status)
	{
		this.status = status;
	}

	public Set<Rowtypes> getRowtypeses()
	{
		return rowtypeses;
	}

	public void setRowtypeses(Set<Rowtypes> rowtypeses)
	{
		this.rowtypeses = rowtypeses;
	}

	public Set<Components> getComponentses()
	{
		return componentses;
	}

	public void setComponentses(Set<Components> componentses)
	{
		this.componentses = componentses;
	}

	public Set<Columntypes> getColumntypeses()
	{
		return columntypeses;
	}

	public void setColumntypeses(Set<Columntypes> columntypeses)
	{
		this.columntypeses = columntypeses;
	}

	public Set<Results> getResultses()
	{
		return resultses;
	}

	public void setResultses(Set<Results> resultses)
	{
		this.resultses = resultses;
	}

}
