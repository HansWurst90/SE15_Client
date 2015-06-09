package studeasy.entities;

import java.io.Serializable;



import studeasy.common.*;


public class Homework implements Serializable, IHomework {

	private static final long serialVersionUID = 9153525651671974891L;

	private int homeworkID;
	private String description;

	private ILesson lesson;
	
	public Homework() {}
	
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
	public ILesson getLesson() {
		return lesson;
	}
	public void setLesson(ILesson lesson) {
		this.lesson = lesson;
	}
}
