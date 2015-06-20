package common;

import java.io.Serializable;

/**
 * Room-Transferobjekt für die Kommunikation mit der Client-Applikation 
 * @author Andreas Prischep
 *
 */


public class SubjectTO implements Serializable {

	private static final long serialVersionUID = -1512930899828828250L;
	
	private int subjectID;
	private String description;
	
	public int getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
