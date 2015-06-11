package common;

import java.util.Date;

import de.studeasy.systeminterfaces.InvalidLoginException;



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
	 * @throws InvalidLoginException 
	 */
	public UserLoginResponse login(int personID, String password) ;
	
	public ReturncodeResponse logout(int sessionID);
	
	public ReturncodeResponse createHomework(int sessionID, int lessonID, String description)  ;
	
	public BooleanResponse removeHomework(int sessionID, int homeworkID)  ;// auch ReturncodeResonse
	
	public LessonListResponse getLessonsByDate(int sessionID, Date date);
	
	public LessonByIDResponse findLessonById(int lessonID);
	
	public LessonListResponse getLessonsBySubject(int subjectID,int courseID, Date startDate, Date endDate) ;
	
	public HomeworkListResponse getHomeworksForPupil(int sessionID, Date startDate, Date endDate) ;	
	
}
