package org.test.elevator;

import org.test.elevator.api.ElevatorController;
import org.test.elevator.model.ElevatorButtonEvent;

import com.dano.ElevatorButtonStub;

public class ElevatorSimulator {

	private static final int ZERO = 0;
	private static final String LIFT_2 = "1";
	private static final String LIFT_1 = "0";
	public static final String DEFAULT_URL = "http://localhost:8080/ElevatorMonitor-0.0.1-SNAPSHOT/update";
	
	private ElevatorButtonStub elevator1;
	private ElevatorButtonStub elevator2;
	private ElevatorController controller1;
	private ElevatorController controller2;
	
	public ElevatorSimulator(final String url) {
		controller1 = new ElevatorController(LIFT_1, url);
		elevator1 = new ElevatorButtonStub(controller1, controller1);
		controller2 = new ElevatorController(LIFT_2, url);
		elevator2 = new ElevatorButtonStub(controller2, controller2);
	}
	
	protected void run() {
		ElevatorButtonEvent event;
		while ((event = ElevatorButtonEventFactory.nextButtonEvent()) != null) {
			String liftId = event.getLiftId();
			if (LIFT_1.equals(liftId)) {
				elevator1.pressButton(event.getButton());
			}
			else if (LIFT_2.equals(liftId)) {
				elevator2.pressButton(event.getButton());
			}
			else {
				System.err.println("Unkown lift Id: " + liftId);
			}
		}
	}
	
	public static void main(String[] args) {
		String url;
		if ((null == args) || (ZERO == args.length)) {
			url = DEFAULT_URL;
		}
		else {
			url = args[ZERO];
		}
		new ElevatorSimulator(url).run();
	}

	public ElevatorController getElevator1() {
		return controller1;
	}
	
	public ElevatorController getElevator2() {
		return controller2;
	}
}
