package org.test.elevator.model;

import org.springframework.util.StringUtils;

import com.dano.ElevatorButton;

public class ElevatorButtonEvent {

	private String liftId;
	private ElevatorButton button;
	
	public ElevatorButtonEvent(String liftId, ElevatorButton button) {
		if (StringUtils.isEmpty(liftId)) {
			throw new IllegalArgumentException("Lift ID cannot be empty.");
		}
		if (null == button) {
			throw new IllegalArgumentException("Button cannot be empty.");
		}
		this.liftId = liftId;
		this.button = button;
	}

	public ElevatorButton getButton() {
		return button;
	}

	public String getLiftId() {
		return liftId;
	}

	
}
