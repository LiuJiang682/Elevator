package org.test.elevator;

import org.test.elevator.model.ElevatorButtonEvent;

import com.dano.ElevatorButton;

public class ElevatorButtonEventFactory {

	private static final int TWO = 2;
	private static final int ONE = 1;
	private static final int ZERO = 0;

	public static ElevatorButtonEvent nextButtonEvent() {
		ElevatorButtonEvent event = null;

		String eventString = MyInputStream.nextLine();
		String[] strings = eventString.split(",");
		if (TWO == strings.length) {
			try {
				ElevatorButton button = ElevatorButton.valueOf(strings[ONE].trim().toUpperCase());
				event = new ElevatorButtonEvent(strings[ZERO], button);
			} catch (Exception e) {
				// Ignore
			}
		}
		return event;
	}

}
