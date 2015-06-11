package common;

import java.util.List;

/**
 * Teacher-Transferobjekt für die Kommunikation mit der Client-Applikation 
 * Dieses Transferobjekt erbt von PersonTO
 * @author Andreas Prischep
 *
 */

public class TeacherTO extends PersonTO {
	
	private static final long serialVersionUID = 2405076137604996925L;

	
	private CourseTO course;
	
	
	private List<LessonTO> lessons;


	public CourseTO getCourse() {
		return course;
	}


	public void setCourse(CourseTO course) {
		this.course = course;
	}


	public List<LessonTO> getLessons() {
		return lessons;
	}


	public void setLessons(List<LessonTO> lessons) {
		this.lessons = lessons;
	}
	
}
