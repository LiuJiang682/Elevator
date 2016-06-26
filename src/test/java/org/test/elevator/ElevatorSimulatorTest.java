package org.test.elevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
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
		ElevatorSimulator simulator = new ElevatorSimulator(ElevatorSimulator.DEFAULT_URL);
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
	
	/**
	 * Given the user can access the ElevatorSimulator
	 * When the main method called without parameters
	 * Then the simulator use default url
	 * @throws Exception 
	 */
	@Test
	public void whenMainCalledWithNullParameterThenDefaultURLShouldBeUse() throws Exception {
		ElevatorSimulator mockSimulator = PowerMockito.mock(ElevatorSimulator.class);
		PowerMockito.whenNew(ElevatorSimulator.class).withArguments(Matchers.anyString()).thenReturn(mockSimulator);
		ElevatorSimulator.main(null);
		
		ArgumentCaptor<String> urlCapture = ArgumentCaptor.forClass(String.class);
		PowerMockito.verifyNew(ElevatorSimulator.class).withArguments(urlCapture.capture());
		assertEquals(ElevatorSimulator.DEFAULT_URL, urlCapture.getValue());
	}
	
	/**
	 * Given the user can access the ElevatorSimulator
	 * When the main method called without parameters
	 * Then the simulator use default url
	 * @throws Exception 
	 */
	@Test
	public void whenMainCalledWithParameterThenDefaultURLShouldBeUse() throws Exception {
		ElevatorSimulator mockSimulator = PowerMockito.mock(ElevatorSimulator.class);
		PowerMockito.whenNew(ElevatorSimulator.class).withArguments(Matchers.anyString()).thenReturn(mockSimulator);
		String[] args = {"http://localhost:8080"};
		ElevatorSimulator.main(args);
		
		ArgumentCaptor<String> urlCapture = ArgumentCaptor.forClass(String.class);
		PowerMockito.verifyNew(ElevatorSimulator.class).withArguments(urlCapture.capture());
		assertEquals("http://localhost:8080", urlCapture.getValue());
	}

	private void givenSimulator() {
		simulator = new ElevatorSimulator(ElevatorSimulator.DEFAULT_URL);
	}
	
	private void givenMockClient() throws Exception {
		mockClient = PowerMockito.mock(RestClient.class);
		PowerMockito.whenNew(RestClient.class).withNoArguments().thenReturn(mockClient);
	}
}
