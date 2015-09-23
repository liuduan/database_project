package edu.tamu.ctv.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import edu.tamu.ctv.entity.enums.Access;
import edu.tamu.ctv.entity.enums.Status;

public class Projects implements java.io.Serializable
{
	private Long id;
	private Projecttypes projecttypes;
	private String code;
	private String name;
	private Access access;
	private String notes;
	private Date starts;
	private Date ends;
	private Status status;
	
	private Users createdby;
	private Users lastmodifiedby;
	private Date registereddt;
	private Date lastupdatedt;
	private Long version;
	
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

	public Projects(Long id, Projecttypes projecttypes, Users createdby, Users lastmodifiedby, String code, String name, Access access, Status status, Date registereddt, Date lastupdatedt, Long version)
	{
		this.id = id;
		this.projecttypes = projecttypes;
		this.createdby = createdby;
		this.lastmodifiedby = lastmodifiedby;
		this.code = code;
		this.name = name;
		this.access = access;
		this.status = status;
		this.registereddt = registereddt;
		this.lastupdatedt = lastupdatedt;
		this.version = version;
	}

	public Projects(Long id, Projecttypes projecttypes, Users createdby, String code, String name, Access access, Status status, String notes, Date registereddt, Date lastupdatedt, Set<Rowtypes> rowtypeses,
			Set<Components> componentses, Set<Columntypes> columntypeses, Set<Results> resultses, Long version)
	{
		this.id = id;
		this.projecttypes = projecttypes;
		this.createdby = createdby;
		this.code = code;
		this.name = name;
		this.access = access;
		this.status = status;
		this.notes = notes;
		this.registereddt = registereddt;
		this.lastupdatedt = lastupdatedt;
		this.rowtypeses = rowtypeses;
		this.componentses = componentses;
		this.columntypeses = columntypeses;
		this.resultses = resultses;
		this.resultses = resultses;
		this.version = version;
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

	public Access getAccess()
	{
		return this.access;
	}

	public void setAccess(Access access)
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

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
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

	public Users getCreatedby()
	{
		return createdby;
	}

	public void setCreatedby(Users createdby)
	{
		this.createdby = createdby;
	}

	public Users getLastmodifiedby()
	{
		return lastmodifiedby;
	}

	public void setLastmodifiedby(Users lastmodifiedby)
	{
		this.lastmodifiedby = lastmodifiedby;
	}

	public Date getRegistereddt()
	{
		return registereddt;
	}

	public void setRegistereddt(Date registereddt)
	{
		this.registereddt = registereddt;
	}

	public Date getLastupdatedt()
	{
		return lastupdatedt;
	}

	public void setLastupdatedt(Date lastupdatedt)
	{
		this.lastupdatedt = lastupdatedt;
	}

	public Long getVersion()
	{
		return version;
	}

	public void setVersion(Long version)
	{
		this.version = version;
	}

}
