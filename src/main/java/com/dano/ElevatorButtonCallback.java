package com.dano;

public interface ElevatorButtonCallback {
	public void buttonPressed(ElevatorButton button);
	
	public void movingElevatorToFloor(int floor);
}
