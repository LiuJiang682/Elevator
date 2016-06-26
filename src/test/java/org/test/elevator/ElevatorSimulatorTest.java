package org.test.elevator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.test.elevator.api.ElevatorController;

import au.com.isobar.rest.RestClient;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ElevatorSimulator.class, ElevatorController.class, RestClient.class,
	MyInputStream.class})
public class ElevatorSimulatorTest {

	private RestClient mockClient;
	private ElevatorSimulator simulator;
	
	/**
	 * Given the user can access the ElevatorSimulator
	 * When the constructor called
	 * Then the simulator object return
	 * @throws Exception 
	 */
	@Test
	public void whenConstructorCalledThenSimulatorShouldReturn() throws Exception {
		//Given the user can access the ElevatorSimulator
		givenMockClient();
		//When the constructor called
		ElevatorSimulator simulator = new ElevatorSimulator();
		//Then the object should return
		assertNotNull(simulator);
	}
	
	/**
	 * Given the user can access the ElevatorSimulator
	 * When the constructor called
	 * Then the simulator object return
	 * @throws Exception 
	 */
	@Test
	public void whenRunCalledWithUpCommandThenFloorShould2() throws Exception {
		//Given the user can access the ElevatorSimulator
		givenMockClient();
		givenSimulator();
		PowerMockito.mockStatic(MyInputStream.class);
		PowerMockito.when(MyInputStream.nextLine()).thenReturn("0, UP", "");
		//When the constructor called
		simulator.run();
		//Then the 2 should return
		assertTrue(2 == simulator.getElevator1().getFloor());
	}

	private void givenSimulator() {
		simulator = new ElevatorSimulator();
	}
	
	private void givenMockClient() throws Exception {
		mockClient = PowerMockito.mock(RestClient.class);
		PowerMockito.whenNew(RestClient.class).withNoArguments().thenReturn(mockClient);
	}
}
