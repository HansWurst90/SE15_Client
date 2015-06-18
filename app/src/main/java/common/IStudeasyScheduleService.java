package common;

import java.util.Date;

// import de.studeasy.systeminterfaces.InvalidLoginException;



/**
 * Dieses Interface definiert die Schnittstelle zwischen der Java app und dem Server.
 * Es wird von Remote abgeleitet, um Remote-Aufrufe vom Client zum Server zu ermoeglichen.
 * 
 * @author Prischep  
 */

public interface IStudeasyScheduleService {

	public static final String INTERFACE_ID = "IStudeasyScheduleService";

	public UserLoginResponse login(int personID, String password)throws Exception ;
	
	public ReturncodeResponse logout(int sessionID)throws Exception;
	
	public ReturncodeResponse createHomework(int sessionID, int lessonID, String description)throws Exception  ;
	
	public BooleanResponse removeHomework(int sessionID, int homeworkID)throws Exception  ;// auch ReturncodeResonse
	
	public LessonListResponse getLessonsByDate(int sessionID, Date date)throws Exception;
	
	public LessonByIDResponse findLessonById(int lessonID)throws Exception;
	
	public LessonListResponse getLessonsBySubject(int subjectID,int courseID, Date startDate, Date endDate)throws Exception ;
	
	public HomeworkListResponse getHomeworksForPupil(int sessionID, Date startDate, Date endDate)throws Exception ;
	
}
