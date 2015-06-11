package common;

import java.util.List;

/**
 * 
 * LessonListResponse wird vom message system benutzt um eine liste von lessons zurückzugeben
 * @author Andreas Prischep
 *
 */


public class LessonListResponse extends ReturncodeResponse {
	

	private static final long serialVersionUID = -8588591620795492047L;

	
	private List<LessonTO> lessonList; 
	
	
	public List<LessonTO> getLessonList() {
		return lessonList;
	}
	
	public void setLessonList(List<LessonTO> lessonList) {
		this.lessonList = lessonList;
		
	}

	
	

	

}
