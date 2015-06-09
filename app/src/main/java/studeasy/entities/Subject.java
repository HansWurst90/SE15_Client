package studeasy.entities;

import java.io.Serializable;

import studeasy.common.*;

public class Subject implements Serializable, ISubject {

	private static final long serialVersionUID = -1512930899828828250L;

	private int subjectID;
	private String description;
	
	public Subject() {}
	
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
