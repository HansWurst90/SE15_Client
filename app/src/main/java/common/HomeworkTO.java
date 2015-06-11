package common;

import java.io.Serializable;

/**
 * Homework-Transferobjekt für die Kommunikation mit der Client-Applikation 
 * @author Andreas Prischep
 *
 */

public class HomeworkTO implements Serializable {

	

	private static final long serialVersionUID = -2470062635073768944L;
	
	private int homeworkID;
	private String description;
	
	
	private LessonTO lesson;
	
	
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
	public LessonTO getLesson() {
		return lesson;
	}
	public void setLesson(LessonTO lesson) {
		this.lesson = lesson;
	}
}
