package au.com.isobar.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RestClientIT {

	private RestClient client;
	
	/**
	 * Given the Monitor system is on
	 * When the update method called
	 * Then the data send to monitor system
	 */
	@Test
	public void whenUpdateCalledThenDataUpload() {
		client = new RestClient();
		String liftId = "0";
		String moving = "up";
		String floor = "3";
		String lockStatus = "unlock";
		String url = "http://localhost:8080/update";
		String message = client.update(url, liftId, moving, floor, lockStatus);
		assertEquals("Ok", message);
	}
}
