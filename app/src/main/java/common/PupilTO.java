package common;

/**
 * Pupil-Transferobjekt für die Kommunikation mit der Client-Applikation.
 * Dieses Transferobjekt erbt von PersonTO
 * @author Andreas Prischep
 *
 */

public class PupilTO extends PersonTO {
	
	
	private static final long serialVersionUID = 3461606884540372275L;
	
	private CourseTO course;
	
	public CourseTO getCourse() {
		return course;
	}

	public void setCourse(CourseTO course) {
		this.course = course;
	}
}
