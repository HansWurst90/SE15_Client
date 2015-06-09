package studeasy.common;

import java.util.Date;
import java.util.List;

/**
 * Dieses Interface definiert die Schnittstelle zwischen der Java app und dem Server.
 * Es wird von Remote abgeleitet, um Remote-Aufrufe vom Client zum Server zu ermoeglichen.
 * 
 * @author Prischep  
 */

public interface IStudeasyScheduleService {

	public static final String INTERFACE_ID = "IStudeasyScheduleService";
	
	/**
	 * Operation zum Einloggen mit Username und Password.
	 * @param personID
	 * @param password
	 * @return boolean successful 
	 */
	public boolean login(int personID, String password) ;
	
	public void logout() throws NoSessionException  ;
	
	public boolean createHomework(int lessonID, String description)  ;
	
	public boolean removeHomework(int homeworkID)  ;
	
	public List<ILesson> getLessonsByDate(int personID, Date date) ;
	
	public ILesson findLessonById(int lessonID) ;
	
	public List<ILesson> getLessonsBySubject(int subjectID,int courseID, Date startDate, Date endDate) ;
	
	public List<IHomework> getHomeworksForPupil(int personID, Date startDate, Date endDate) ;	
	
}
