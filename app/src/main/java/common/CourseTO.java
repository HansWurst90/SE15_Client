package common;

import java.io.Serializable;


import java.util.List;


/**
 * Curse-Transferobjekt für die Kommunikation mit der Client-Applikation 
 * @author Andreas Prischep
 *
 */


public class CourseTO implements Serializable {
	
	private static final long serialVersionUID = -3906372330041256764L;


	private int courseID;
	
	private int grade;
	//e.g. 5'B'
	private char descriptor;
	
	private TeacherTO classTeacher;
	
	private List<PupilTO> pupils;
	
	private List<LessonTO> lessons;
	
	
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
	public TeacherTO getClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(TeacherTO classTeacher) {
		this.classTeacher = classTeacher;
	}
	public List<PupilTO> getPupils() {
		return pupils;
	}
	public void setPupils(List<PupilTO> pupils) {
		this.pupils = pupils;
	}
	public List<LessonTO> getLessons() {
		return lessons;
	}
	public void setLessons(List<LessonTO> lessons) {
		this.lessons = lessons;
	}
}
