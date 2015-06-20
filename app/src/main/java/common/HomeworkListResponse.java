package common;

import java.util.List;


public class HomeworkListResponse extends ReturncodeResponse {
	
	
	private static final long serialVersionUID = -3082296295638916589L;
	
	private List<HomeworkTO> homeworklist; 
	
	public List<HomeworkTO> getHomeworkList(){
		return homeworklist;
	}
	
	public void setHomeworkList(List<HomeworkTO> homeworklist) {
		this.homeworklist = homeworklist;
		
	}
}
