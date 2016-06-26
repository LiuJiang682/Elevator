package com.dano;

public enum ElevatorButton {
	DOWN (0),
	UP (0),
	FLOOR_1 (1),
	FLOOR_2 (2),
	FLOOR_3 (3),
	FLOOR_4 (4),
	FLOOR_5 (5),
	FLOOR_6 (6),
	LOCK (0),
	UNLOCK (0);
	
	public int floor;
	
	private ElevatorButton(int floor) {
		this.floor = floor;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
}
