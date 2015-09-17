package edu.tamu.ctv.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import edu.tamu.ctv.entity.enums.Roles;

public class Users implements Serializable
{
	private static final long serialVersionUID = 8955989208010581715L;
	
	private Long id;
	private String login;
	private String password;
	private Roles  role = Roles.USER;
	private String confirmPassword;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String address1;
	private String address2;
	private String country;
	private String city;
	private String website;
	private String interests;
	private Date   birthday;
	private String state;
	private String zip;
	private String sex;
	private String organization;
	private String organaddress;
	private byte[] photo;
	private String notes;
	private Date registereddt;
	private Date lastvisitdt;
	private Set<Results> resultses = new HashSet<Results>(0);
	private Set<Importinfo> importinfos = new HashSet<Importinfo>(0);
	private Set<Components> componentses = new HashSet<Components>(0);
	private Set<Resultshistory> resultshistories = new HashSet<Resultshistory>(0);

	private Set<Notes> noteses = new HashSet<Notes>(0);
	private Set<Units> unitses = new HashSet<Units> (0);
	
	private Set<Projects> projectses = new HashSet<Projects>(0);
	
	private Set<Projects> projectreviewerses = new HashSet<Projects>(0);
	private Set<Projects> projectmanagerses = new HashSet<Projects>(0);
	private Set<Projects> projectmemberses = new HashSet<Projects>(0);

	public boolean isNew()
	{
		return null == id;
	}
	
	public String getFullName()
	{
		return firstname + " " + lastname;
	}
	
	public Users()
	{
	}

	public Users(Long id, String login, String password, String firstname, String lastname, String email, String phone, String address1, String country, String state, String zip, String sex,
			Date registereddt, Date lastvisitdt, Date birthday, String city)
	{
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address1 = address1;
		this.country = country;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.sex = sex;
		this.registereddt = registereddt;
		this.lastvisitdt = lastvisitdt;
		this.birthday = birthday;
		
	}

	public Users(Long id, String login, String password, String firstname, String lastname, String email, String phone, String address1, String address2, String country, String state, String zip,
			String sex, String organization, String organaddress, byte[] photo, String notes, Date registereddt, Date lastvisitdt, Set<Results> resultses, Set<Importinfo> importinfos, Set<Components> componentses, Set<Resultshistory> resultshistories,
			Set<Notes> noteses, Set<Projects> projectreviewerses, Set<Projects> projectmanagerses, Set<Projects> projectmemberses, Set<Units> unitses, Set<Projects> projectses)
	{
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.country = country;
		this.state = state;
		this.zip = zip;
		this.sex = sex;
		this.organization = organization;
		this.organaddress = organaddress;
		this.photo = photo;
		this.notes = notes;
		this.registereddt = registereddt;
		this.lastvisitdt = lastvisitdt;
		this.resultses = resultses;
		this.importinfos = importinfos;
		this.componentses = componentses;
		this.resultshistories = resultshistories;
		this.noteses = noteses;
		this.projectreviewerses = projectreviewerses;
		this.projectmanagerses = projectmanagerses;
		this.projectmemberses = projectmemberses;
		this.unitses = unitses;
		this.projectses = projectses;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	public Roles getRole()
	{
		return role;
	}

	public void setRole(Roles role)
	{
		this.role = role;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAddress1()
	{
		return address1;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getWebsite()
	{
		return website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	public String getInterests()
	{
		return interests;
	}

	public void setInterests(String interests)
	{
		this.interests = interests;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getOrganization()
	{
		return organization;
	}

	public void setOrganization(String organization)
	{
		this.organization = organization;
	}

	public String getOrganaddress()
	{
		return organaddress;
	}

	public void setOrganaddress(String organaddress)
	{
		this.organaddress = organaddress;
	}

	public byte[] getPhoto()
	{
		return photo;
	}

	public void setPhoto(byte[] photo)
	{
		this.photo = photo;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public Date getRegistereddt()
	{
		return registereddt;
	}

	public void setRegistereddt(Date registereddt)
	{
		this.registereddt = registereddt;
	}

	public Date getLastvisitdt()
	{
		return lastvisitdt;
	}

	public void setLastvisitdt(Date lastvisitdt)
	{
		this.lastvisitdt = lastvisitdt;
	}

	public Set<Results> getResultses()
	{
		return resultses;
	}

	public void setResultses(Set<Results> resultses)
	{
		this.resultses = resultses;
	}

	public Set<Importinfo> getImportinfos()
	{
		return importinfos;
	}

	public void setImportinfos(Set<Importinfo> importinfos)
	{
		this.importinfos = importinfos;
	}

	public Set<Components> getComponentses()
	{
		return componentses;
	}

	public void setComponentses(Set<Components> componentses)
	{
		this.componentses = componentses;
	}

	public Set<Resultshistory> getResultshistories()
	{
		return resultshistories;
	}

	public void setResultshistories(Set<Resultshistory> resultshistories)
	{
		this.resultshistories = resultshistories;
	}

	public Set<Notes> getNoteses()
	{
		return noteses;
	}

	public void setNoteses(Set<Notes> noteses)
	{
		this.noteses = noteses;
	}

	public Set<Units> getUnitses()
	{
		return unitses;
	}

	public void setUnitses(Set<Units> unitses)
	{
		this.unitses = unitses;
	}

	public Set<Projects> getProjectses()
	{
		return projectses;
	}

	public void setProjectses(Set<Projects> projectses)
	{
		this.projectses = projectses;
	}

	public Set<Projects> getProjectreviewerses()
	{
		return projectreviewerses;
	}

	public void setProjectreviewerses(Set<Projects> projectreviewerses)
	{
		this.projectreviewerses = projectreviewerses;
	}

	public Set<Projects> getProjectmanagerses()
	{
		return projectmanagerses;
	}

	public void setProjectmanagerses(Set<Projects> projectmanagerses)
	{
		this.projectmanagerses = projectmanagerses;
	}

	public Set<Projects> getProjectmemberses()
	{
		return projectmemberses;
	}

	public void setProjectmemberses(Set<Projects> projectmemberses)
	{
		this.projectmemberses = projectmemberses;
	}

}
