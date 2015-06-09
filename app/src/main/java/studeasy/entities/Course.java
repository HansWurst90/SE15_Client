package studeasy.entities;

import java.io.Serializable;

import java.util.ArrayList;

import studeasy.common.*;


public class Course implements Serializable, ICourse {
	
	private static final long serialVersionUID = -3906372330041256764L;

	private int courseID;
	
	private int grade;
	//e.g. 5'B'
	private char descriptor;

	private ITeacher classTeacher;

	private ArrayList<IPupil> pupils;

	private ArrayList<ILesson> lessons;
	
	public Course() {}
	
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public char getDescriptor() {
		return descriptor;
	}
	public void setDescriptor(char descriptor) {
		this.descriptor = descriptor;
	}
	public ITeacher getClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(ITeacher classTeacher) {
		this.classTeacher = classTeacher;
	}
	public ArrayList<IPupil> getPupils() {
		return pupils;
	}
	public void setPupils(ArrayList<IPupil> pupils) {
		this.pupils = pupils;
	}
	public ArrayList<ILesson> getLessons() {
		return lessons;
	}
	public void setLessons(ArrayList<ILesson> lessons) {
		this.lessons = lessons;
	}
}
