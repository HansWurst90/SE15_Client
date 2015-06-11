package common;

import java.io.Serializable;
import java.util.List;

/**
 * Room-Transferobjekt für die Kommunikation mit der Client-Applikation 
 * @author Andreas Prischep
 *
 */

public class RoomTO implements Serializable {

	private static final long serialVersionUID = 2206874152902693717L;


	private String roomID;
	

	private List<LessonTO> lessons;

	
	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public List<LessonTO> getLessons() {
		return lessons;
	}

	public void setLessons(List<LessonTO> lessons) {
		this.lessons = lessons;
	}
}
