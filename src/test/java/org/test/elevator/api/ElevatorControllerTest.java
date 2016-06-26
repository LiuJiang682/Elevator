package org.test.elevator.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dano.ElevatorButton;

import au.com.isobar.rest.RestClient;

/**
 * Test class for ElevatorController
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ElevatorController.class, RestClient.class})
public class ElevatorControllerTest {

	private static final String TEST_LIFT_ID = "1";

	private static final String TEST_URL = "http://localhost:8080";

	private ElevatorController controller;

	private RestClient mockClient;

	/**
	 * Given the user can access the controller 
	 * When the constructor called with null parameter
	 * Then IllegalArgumentException raised
	 */
	@Test
	public void whenConstructoredCalledWithNullParametersThenIllegalArgumentExceptonShouldRaise() {
		try {
			new ElevatorController(null, null);
			fail("Program reached unexpected point");
		}
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Lift ID cannot be empty.", message);
		}
	}
	
	/**
	 * Given the user can access the controller 
	 * When the constructor called with null parameter
	 * Then IllegalArgumentException raised
	 */
	@Test
	public void whenConstructoredCalledWithNullUrlThenIllegalArgumentExceptonShouldRaise() {
		try {
			new ElevatorController("1", null);
			fail("Program reached unexpected point");
		}
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("URL is invalid.", message);
		}
	}
	
	/**
	 * Given the user can access the controller 
	 * When the constructor called with null parameter
	 * Then IllegalArgumentException raised
	 */
	@Test
	public void whenConstructoredCalledWithInvalidUrlThenIllegalArgumentExceptonShouldRaise() {
		try {
			new ElevatorController("1", "abc");
			fail("Program reached unexpected point");
		}
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("URL is invalid.", message);
		}
	}
	
	/**
	 * Given the user can access the controller 
	 * When the constructor called Then
	 * the controller should return
	 * @throws Exception 
	 */
	@Test
	public void whenConstructorCalledThenControllerShouldReturn() throws Exception {
		givenMockClient();
		givenLift();
		// Then the controller should be exist
		assertNotNull(controller);
		assertEquals(TEST_LIFT_ID, controller.getLiftId());
		assertEquals(ElevatorController.DEFAULT_MOVING, controller.getMoving());
		assertTrue(1 == controller.getFloor());
		assertEquals(ElevatorController.DEFAULT_LOCK_STATUS, controller.getLockStatus());
	}

	/**
	 * Given the controller on the ground floor When the moveDownOneFloor called
	 * Then the lift floor should not change
	 */
	@Test
	public void whenMoveDownOneFloorCalledWithFloorOneThenFloorShouldNOTChange() {
		// Given the user can access the controller
		givenPartialMockController();
		initMockFloor();
		PowerMockito.doCallRealMethod().when(controller).moveDownOneFloor();
		
		// When the moveDownOneFloor called
		controller.moveDownOneFloor();
		// Then the floor should not change
		assertEquals(1, controller.getFloor());
		verify(controller, times(0)).sendDataToServer();
	}

	/**
	 * Given the controller on the ground floor When the moveUpOneFloor called
	 * Then the lift floor should change
	 */
	@Test
	public void whenMoveUpOneFloorCalledWithFloorOneThenFloorShouldChange() {
		// Given the user can access the controller
		givenPartialMockController();
		initMockFloor();
		PowerMockito.doCallRealMethod().when(controller).moveUpOneFloor();
		
		// When the moveUpOneFloor called
		controller.moveUpOneFloor();
		// Then the floor should not change
		assertTrue(2 == controller.getFloor());
		verify(controller, times(1)).sendDataToServer();
	}

	

	/**
	 * Given the controller on the ground floor When the moveUpOneFloor called
	 * Then the lift floor should change
	 */
	@Test
	public void whenMoveUpOneFloorCalledWithFloorSixThenFloorShouldNOTChange() {
		// Given the user can access the controller
		givenPartialMockController();
		initMockFloor();
		PowerMockito.doCallRealMethod().when(controller).moveUpOneFloor();

		controller.moveUpOneFloor();
		controller.moveUpOneFloor();
		controller.moveUpOneFloor();
		controller.moveUpOneFloor();
		controller.moveUpOneFloor();

		// When the moveUpOneFloor called
		controller.moveUpOneFloor();

		// Then the floor should not change
		assertTrue(6 == controller.getFloor());
		verify(controller, times(5)).sendDataToServer();
	}

	/**
	 * Given the controller on the ground floor When the lockBreak called Then
	 * the lift lock status should be lock
	 * @throws Exception 
	 */
	@Test
	public void whenLockBreakCalledThenLockStatusShouldLock() throws Exception {
		givenMockClient();
		// Given the user can access the controller
		givenLift();
		// When the lockBreaks called
		controller.lockBreaks();
		// Then the status should be lock
		assertEquals(ElevatorController.LOCK_BREAK, controller.getLockStatus());
	}

	/**
	 * Given the controller on the ground floor When the unlockBreak called Then
	 * the lift lock status should be unlock
	 * @throws Exception 
	 */
	@Test
	public void whenUnlockBreakCalledThenLockStatusShouldUnlock() throws Exception {
		givenMockClient();
		// Given the user can access the controller
		givenLift();
		// When the unlockBreaks called
		controller.unlockBreaks();
		// Then the status should be lock
		assertEquals(ElevatorController.UNLOCK_BREAK, controller.getLockStatus());
	}

	/**
	 * Given the controller on the one floor When the moveDownOneFloor called
	 * Then the lift floor should change
	 */
	@Test
	public void whenMoveDownOneFloorCalledWithFloorOneThenFloorShouldChange() {
		// Given the user can access the controller
		givenPartialMockController();
		initMockFloor();
		PowerMockito.doCallRealMethod().when(controller).moveUpOneFloor();
		PowerMockito.doCallRealMethod().when(controller).moveDownOneFloor();
		controller.moveUpOneFloor();
		// When the moveDownOneFloor called
		controller.moveDownOneFloor();
		// Then the floor should not change
		assertTrue(1 == controller.getFloor());
		verify(controller, times(2)).sendDataToServer();
	}

	/**
	 * Given the controller on the one floor When the null buttonPress called
	 * Then the lift should not change
	 * @throws Exception 
	 */
	@Test
	public void whenNullButtonPressWithFloorTwoThenLiftShouldNotChange() throws Exception {
		givenMockClient();
		// Given the user can access the controller
		givenLift();
		
		controller.moveUpOneFloor();
		// When the Down button pressed
		controller.buttonPressed(null);
		// Then the floor should not change
		assertTrue(2 == controller.getFloor());
	}
	
	/**
	 * Given the controller on the one floor 
	 * When the moveToDestFloor called
	 * Then the lift destFloor should be updated
	 * @throws Exception 
	 */
	@Test
	public void whenMoveToDestFloorCalledThenDestFloorShouldBeUpdated() throws Exception {
		givenMockClient();
		// Given the user can access the controller
		givenLift();
		
		controller.moveUpOneFloor();
		// When the movingElevatorToFloor called
		controller.movingElevatorToFloor(5);
		
		// Then the destFloor should be updated
		assertTrue(5 == controller.getDestFloor());
	}
	
	/**
	 * Given the controller on the one floor 
	 * When the moveToDestFloor called with invalid floor
	 * Then the lift destFloor should be NOT updated
	 * @throws Exception 
	 */
	@Test
	public void whenMoveToDestFloorCalledWithBlowMinThenDestFloorShouldNOTBeUpdated() throws Exception {
		givenMockClient();
		// Given the user can access the controller
		givenLift();
		
		controller.moveUpOneFloor();
		// When the movingElevatorToFloor called
		controller.movingElevatorToFloor(0);
		
		// Then the destFloor should be updated
		assertTrue(-1 == controller.getDestFloor());
	}
	
	/**
	 * Given the controller on the one floor 
	 * When the moveToDestFloor called with invalid floor
	 * Then the lift destFloor should be NOT updated
	 * @throws Exception 
	 */
	@Test
	public void whenMoveToDestFloorCalledWithAboveMaxThenDestFloorShouldNOTBeUpdated() throws Exception {
		this.givenMockClient();
		// Given the user can access the controller
		givenLift();
		
		controller.moveUpOneFloor();
		// When the movingElevatorToFloor called
		controller.movingElevatorToFloor(7);
		
		// Then the destFloor should not be updated
		assertTrue(-1 == controller.getDestFloor());
	}

	/**
	 * Given the controller on the one floor 
	 * When the Floor ButtonPressed
	 * Then the update not called
	 */
	@Test
	public void whenFloorButtonPressedThenUpdateShouldNotCalled() {
		// Given the user can access the controller
		givenPartialMockController();
		PowerMockito.doCallRealMethod().when(controller).buttonPressed(Matchers.any(ElevatorButton.class));
		
		// When the lock button pressed
		controller.buttonPressed(ElevatorButton.FLOOR_2);
		
		// Then the break should be lock
		verify(controller, times(0)).sendDataToServer();
	}
	
	/**
	 * Given the user can access the controller
	 * When the sendDataToServer method called
	 * Then data send
	 * @throws Exception 
	 */
	@Test
	public void whenSendDataToServerCalledThenDataSend() throws Exception {
		givenMockClient();
		//Given the lift
		givenLift();
		
		//When the sendDataToServer method called
		controller.sendDataToServer();
		//Then data should be send
		verify(mockClient, times(2)).update(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), Matchers.anyString());
	}

	private void givenMockClient() throws Exception {
		mockClient = PowerMockito.mock(RestClient.class);
		PowerMockito.whenNew(RestClient.class).withNoArguments().thenReturn(mockClient);
	}

	private void givenPartialMockController() {
		controller = PowerMockito.mock(ElevatorController.class);
	}
	
	private void givenLift() {
		controller = new ElevatorController(TEST_LIFT_ID, TEST_URL);
	}
	private void initMockFloor() {
		Whitebox.setInternalState(controller, "floor", 1);
		PowerMockito.doCallRealMethod().when(controller).getFloor();
	}
}
