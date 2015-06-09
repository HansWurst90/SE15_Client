package studeasy.entities;

import java.io.Serializable;

import studeasy.common.*;
import java.util.ArrayList;

public class Room implements Serializable, IRoom {

	private static final long serialVersionUID = 2206874152902693717L;

	private String roomID;

	private ArrayList<ILesson> lessons;

	public Room() {}
	
	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public ArrayList<ILesson> getLessons() {
		return lessons;
	}

	public void setLessons(ArrayList<ILesson> lessons) {
		this.lessons = lessons;
	}
}
