package edu.tamu.ctv.entity;
// Generated Sep 5, 2015 1:43:34 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Units generated by hbm2java
 */
public class Units implements java.io.Serializable
{

	private Long id;
	private Units units;
	private Users users;
	private String code;
	private String name;
	private BigDecimal volumeof;
	private Date updateddt;
	private Set unitses = new HashSet(0);
	private Set componentses = new HashSet(0);

	public Units()
	{
	}

	public Units(Long id, Users users, String code, Date updateddt)
	{
		this.id = id;
		this.users = users;
		this.code = code;
		this.updateddt = updateddt;
	}

	public Units(Long id, Units units, Users users, String code, String name, BigDecimal volumeof, Date updateddt, Set unitses, Set componentses)
	{
		this.id = id;
		this.units = units;
		this.users = users;
		this.code = code;
		this.name = name;
		this.volumeof = volumeof;
		this.updateddt = updateddt;
		this.unitses = unitses;
		this.componentses = componentses;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Units getUnits()
	{
		return this.units;
	}

	public void setUnits(Units units)
	{
		this.units = units;
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

	public BigDecimal getVolumeof()
	{
		return this.volumeof;
	}

	public void setVolumeof(BigDecimal volumeof)
	{
		this.volumeof = volumeof;
	}

	public Date getUpdateddt()
	{
		return this.updateddt;
	}

	public void setUpdateddt(Date updateddt)
	{
		this.updateddt = updateddt;
	}

	public Set getUnitses()
	{
		return this.unitses;
	}

	public void setUnitses(Set unitses)
	{
		this.unitses = unitses;
	}

	public Set getComponentses()
	{
		return this.componentses;
	}

	public void setComponentses(Set componentses)
	{
		this.componentses = componentses;
	}

}
