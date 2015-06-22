package de.jalt.studeasy.common;

import java.io.Serializable;

/**
 * Homework-Transferobjekt für die Kommunikation mit der Client-Applikation 
 * @author Andreas Prischep, Tobias Riegel
 *
 */

public class HomeworkTO implements Serializable {

	

	private static final long serialVersionUID = -2470062635073768944L;
	
	private int homeworkID;
	private String description;
	
	
	public int getHomeworkID() {
		return homeworkID;
	}
	public void setHomeworkID(int homeworkID) {
		this.homeworkID = homeworkID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
