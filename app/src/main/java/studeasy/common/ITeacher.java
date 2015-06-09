package studeasy.common;

import java.util.ArrayList;

public interface ITeacher extends IPerson {

	public ICourse getCourse();
	public void setCourse(ICourse course);
	public ArrayList<ILesson> getLessons();
	public void setLessons(ArrayList<ILesson> lessons);
}
