package org.test.elevator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.test.elevator.model.ElevatorButtonEvent;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ElevatorButtonEventFactory.class, MyInputStream.class})
public class ElevatorButtonEventFactoryTest {

	/**
	 * Given user can access ElevatorButtonFactory
	 * When the nextButton method called
	 * Then the next button object return
	 */
	@Test
	public void whenNextButtonCalledThenButtonObjectReturn() {
		//Given user can access ElevatorButtonFactory
		PowerMockito.mockStatic(MyInputStream.class);
		PowerMockito.when(MyInputStream.nextLine()).thenReturn("1, UP");
		//When the nextButton method called
		ElevatorButtonEvent event = ElevatorButtonEventFactory.nextButtonEvent();
		assertNotNull(event);
	}
	
	/**
	 * Given user can access ElevatorButtonFactory
	 * When the nextButton method called
	 * Then the next button object return
	 */
	@Test
	public void whenNextButtonCalledWithInvalidStringThenNullReturn() {
		//Given user can access ElevatorButtonFactory
		PowerMockito.mockStatic(MyInputStream.class);
		PowerMockito.when(MyInputStream.nextLine()).thenReturn("1, abc");
		//When the nextButton method called
		ElevatorButtonEvent event = ElevatorButtonEventFactory.nextButtonEvent();
		assertNull(event);
	}
}
