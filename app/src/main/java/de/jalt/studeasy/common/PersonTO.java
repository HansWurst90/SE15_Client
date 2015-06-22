package de.jalt.studeasy.common;

import java.io.Serializable;

/**
 * Person-Transferobjekt für die Kommunikation mit der Client-Applikation 
 * @author Andreas Prischep
 *
 */
public class PersonTO implements Serializable {
	
	private static final long serialVersionUID = -3379697478831033483L;
	
	private int personID;
	private String name;
	private String firstname;
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
	
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
}
