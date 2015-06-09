package studeasy.entities;

import java.util.ArrayList;

import studeasy.common.*;

public class Teacher extends Person implements ITeacher {
	
	private static final long serialVersionUID = 2405076137604996925L;

	private ICourse course;

	private ArrayList<ILesson> lessons;
	
	public Teacher() {}
	
	public ICourse getCourse() {
		return course;
	}

	public void setCourse(ICourse course) {
		this.course = course;
	}

	public ArrayList<ILesson> getLessons() {
		return lessons;
	}

	public void setLessons(ArrayList<ILesson> lessons) {
		this.lessons = lessons;
	}
}
