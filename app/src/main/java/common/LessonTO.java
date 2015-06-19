package common;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Lesson-Transferobjekt für die Kommunikation mit der Client-Applikation 
 * @author Andreas Prischep
 *
 */
public class LessonTO implements Serializable {
	
	private static final long serialVersionUID = -7694338591615228808L;
	

	private int lessonID;
	private int lessonHour;
	private String date;
	

	private CourseTO course;
	private TeacherTO teacher;
	private SubjectTO subject;
	private String room;
	private List<HomeworkTO> homeworks;
	public int getLessonID() {
		return lessonID;
	}
	public void setLessonID(int lessonID) {
		this.lessonID = lessonID;
	}
	public int getLessonHour() {
		return lessonHour;
	}
	public void setLessonHour(int lessonHour) {
		this.lessonHour = lessonHour;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public CourseTO getCourse() {
		return course;
	}
	public void setCourse(CourseTO course) {
		this.course = course;
	}
	public TeacherTO getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherTO teacher) {
		this.teacher = teacher;
	}
	public SubjectTO getSubject() {
		return subject;
	}
	public void setSubject(SubjectTO subject) {
		this.subject = subject;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public List<HomeworkTO> getHomeworks() {
		return homeworks;
	}
	public void setHomeworks(List<HomeworkTO> homeworks) {
		this.homeworks = homeworks;
	}
	
}
