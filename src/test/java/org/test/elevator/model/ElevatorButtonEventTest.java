package org.test.elevator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.dano.ElevatorButton;

public class ElevatorButtonEventTest {

	/**
	 * Given the user can access the lift button
	 * When the button pressed
	 * Then ElevatorButtonEvent should return
	 */
	@Test
	public void whenConstructorCalledThenObjectShouldReturn() {
		//Given the user can access the lift button
		//When the button pressed
		ElevatorButtonEvent event = new ElevatorButtonEvent("0", ElevatorButton.DOWN);
		//Then object should return
		assertNotNull(event);
		assertEquals("0", event.getLiftId());
		assertEquals(ElevatorButton.DOWN, event.getButton());
	}
	
	/**
	 * Given the user can access the lift button
	 * When the button pressed with null parameters
	 * Then ElevatorButtonEvent should Not return
	 */
	@Test
	public void whenConstructorCalledWithNullThenObjectShouldReturn() {
		//Given the user can access the lift button
		//When the button pressed
		try {
			new ElevatorButtonEvent(null, null);
			fail("Program reached unexpected point");
		}
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Lift ID cannot be empty.", message);
		}
		
	}
	
	/**
	 * Given the user can access the lift button
	 * When the button pressed with null parameters
	 * Then ElevatorButtonEvent should Not return
	 */
	@Test
	public void whenConstructorCalledWithNullButtonThenObjectShouldReturn() {
		//Given the user can access the lift button
		//When the button pressed
		try {
			new ElevatorButtonEvent("0", null);
			fail("Program reached unexpected point");
		}
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Button cannot be empty.", message);
		}
		
	}
}
