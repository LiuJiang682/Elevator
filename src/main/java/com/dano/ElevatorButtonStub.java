package com.dano;

import org.test.elevator.api.ElevatorFacade;


public class ElevatorButtonStub implements ElevatorButtonFacade {

	private int currentFloor = 0;
	private ElevatorFacade facade;
	private ElevatorButtonCallback callback;
	
	public ElevatorButtonStub(ElevatorFacade facade, ElevatorButtonCallback callback){
		this.facade = facade;
		this.callback = callback;
	}
	
	@Override
	public void pressButton(ElevatorButton button) {
		if (button == null){
			throw new IllegalArgumentException("Elevator button cannot be null");
		}

		callback.buttonPressed(button);
		
		if (ElevatorButton.DOWN.equals(button)){
			facade.moveDownOneFloor();
		}else if (ElevatorButton.UP.equals(button)){
			facade.moveUpOneFloor();
		}else if (ElevatorButton.LOCK.equals(button)){
			facade.lockBreaks();
		}else if (ElevatorButton.UNLOCK.equals(button)){
			facade.unlockBreaks();
		}else{
			int destFloor = button.getFloor();
			callback.movingElevatorToFloor(destFloor);
			while (currentFloor < destFloor){
				facade.moveUpOneFloor();
				currentFloor++;
			}
			while(currentFloor > destFloor){
				facade.moveDownOneFloor();
				currentFloor--;
			}
		}
	}
	
}
