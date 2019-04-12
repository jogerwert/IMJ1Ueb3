package de.stl.saar.internetentw1.model;

import java.io.Serializable;

public class Room implements Serializable{
	private int building;
	private int floor;
	private int room;
	
	public Room(int building, int floor, int room) {
		super();
		this.building = building;
		this.floor = floor;
		this.room = room;
	}

	public int getBuilding() {
		return building;
	}
	
	public void setBuilding(int building) {
		this.building = building;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public int getRoom() {
		return room;
	}
	
	public void setRoom(int room) {
		this.room = room;
	}
	
	@Override
	public String toString() {
		return String.valueOf(building) + String.valueOf(floor) + String.valueOf(room);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null){
			return false;
		}	
		
		if(o instanceof Room){
			Room cmpRoom = (Room) o;
			int cmpBuildingNumber = cmpRoom.getBuilding();
			int cmpFloorNumber	= cmpRoom.getFloor();
			int cmpRoomNumber = cmpRoom.getRoom();
			
			return (cmpBuildingNumber == building) && (cmpFloorNumber == floor) && (cmpRoomNumber == room); 
		}else {
			return false;
		}
		
	}
}
