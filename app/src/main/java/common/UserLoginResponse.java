package common;

/**
 * 
 * UserLoginResponse wird vom message system benutzt um sessionsIDs zurückzugeben
 * @author Andreas Prischep, Tobias Riegel
 *
 */

public class UserLoginResponse extends ReturncodeResponse {
	
	private static final long serialVersionUID = -3173158325918408228L;
	
	private int sessionID;
	private String name;
	private String firstname;
	
	public UserLoginResponse() {
	}

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
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

}
