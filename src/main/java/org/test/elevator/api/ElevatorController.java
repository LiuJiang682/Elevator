package org.test.elevator.api;

import java.net.URL;

import org.springframework.util.StringUtils;

import com.dano.ElevatorButton;
import com.dano.ElevatorButtonCallback;

import au.com.isobar.rest.RestClient;

public class ElevatorController implements ElevatorFacade, ElevatorButtonCallback {

	private static final String MOVING_DOWN = "down";
	private static final String MOVING_UP = "up";
	private static final int NOT_INIT = -1;
	public static int MIN = 1;
	public static int MAX = 6;
	
	public static final String UNLOCK_BREAK = "unlock";
	public static final String LOCK_BREAK = "lock";
	public static final String DEFAULT_LOCK_STATUS = UNLOCK_BREAK;
	public static final String DEFAULT_MOVING = MOVING_UP;
	
	private String liftId;
	private String moving;
	private int floor;
	private String lockStatus;
	private URL url;
	private int destFloor = NOT_INIT;
	
	public ElevatorController(final String liftId, final String urlString) {
		if (StringUtils.isEmpty(liftId)) {
			throw new IllegalArgumentException("Lift ID cannot be empty.");
		}
		if (StringUtils.isEmpty(urlString)) {
			throw new IllegalArgumentException("URL is invalid.");
		}
		else {
			try {
				url = new URL(urlString);
				url.toURI();
			}
			catch (Exception e){
				throw new IllegalArgumentException("URL is invalid.");
			} 
		}
		this.liftId = liftId;
		this.moving = DEFAULT_MOVING;
		this.floor = MIN;
		this.lockStatus = DEFAULT_LOCK_STATUS;
		this.sendDataToServer();
	}

	public void moveDownOneFloor() {
		if (MIN < floor) {
			--floor;
			moving = MOVING_DOWN;
			this.sendDataToServer();
		}
	}

	public void moveUpOneFloor() {
		if (floor < MAX) {
			++floor;
			moving = MOVING_UP;
			this.sendDataToServer();
		}
	}

	public void lockBreaks() {
		this.lockStatus = LOCK_BREAK;
		this.sendDataToServer();
	}

	public void unlockBreaks() {
		this.lockStatus = UNLOCK_BREAK;
		this.sendDataToServer();
	}

	public void buttonPressed(ElevatorButton button) {
		//Ignore
	}

	protected void sendDataToServer() {
		RestClient client = new RestClient();
		client.update(url.toString(), liftId, moving, String.valueOf(floor), lockStatus);
	}

	public void movingElevatorToFloor(int floor) {
		if ((MIN < floor) && (floor < MAX))
			this.destFloor = floor;
	}

	public String getLiftId() {
		return this.liftId;
	}

	public String getMoving() {
		return this.moving;
	}

	public int getFloor() {
		return this.floor;
	}

	public String getLockStatus() {
		return this.lockStatus;
	}

	public int getDestFloor() {
		return this.destFloor;
	}
}
