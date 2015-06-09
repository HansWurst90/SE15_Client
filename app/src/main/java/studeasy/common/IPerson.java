package studeasy.common;

import java.io.Serializable;

public interface IPerson extends Serializable {
	
	public int getPersonID();
	public void setPersonID(int personID);
	public String getName();
	public void setName(String name);
	public String getFirstname();
	public void setFirstname(String firstname);
	public char getGender();
	public void setGender(char gender);
	public String getPassword();
	public void setPassword(String password);
}
