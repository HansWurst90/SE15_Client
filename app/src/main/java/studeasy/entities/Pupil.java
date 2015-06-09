package studeasy.entities;

import studeasy.common.*;

public class Pupil extends Person implements IPupil {
	
	
	private static final long serialVersionUID = 3461606884540372275L;

	private ICourse course;
	
	public Pupil() {super();}

	public ICourse getCourse() {
		return course;
	}

	public void setCourse(ICourse course) {
		this.course = course;
	}
}
