package studeasy.common;

import java.io.Serializable;

import java.util.ArrayList;

public interface ICourse extends Serializable {
	
	public int getCourseID();
	
	public void setCourseID(int courseID);
	
	public int getGrade();
	
	public void setGrade(int grade);
	
	public char getDescriptor();
	
	public void setDescriptor(char descriptor);
	
	public ITeacher getClassTeacher();
	
	public void setClassTeacher(ITeacher classTeacher);
	
	public ArrayList<IPupil> getPupils();
	
	public void setPupils(ArrayList<IPupil> pupils);
	
	public ArrayList<ILesson> getLessons();
	
	public void setLessons(ArrayList<ILesson> lessons);

}
