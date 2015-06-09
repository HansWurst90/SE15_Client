package studeasy.entities;

import java.io.Serializable;

import studeasy.common.*;

public abstract class Person implements Serializable, IPerson {
	
	private static final long serialVersionUID = 849222223534910538L;

	private int personID;
	private String name;
	private String firstname;
	private String password;
	//'m'=man 'w'=woman
	private char gender;

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
}
