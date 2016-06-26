package au.com.isobar.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

	public String update(final String urlString,
			final String liftId, final String moving, 
			final String floor, final String lockStatus) {
		String message = "Fail";
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String input = new StringBuilder("{\"liftId\":\"")
					.append(liftId)
					.append("\",\"moving\": \"")
					.append(moving)
					.append("\",\"floor\": \"")
					.append(floor)
					.append("\",\"lockStatus\": \"")
					.append(lockStatus)
					.append("\"}")
					.toString();
			System.out.println(input);
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(input.getBytes());
			outputStream.flush();
			
			if (200 == conn.getResponseCode()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder buf = new StringBuilder();
				String output;
				while ((output = reader.readLine()) != null) {
					System.out.println(output);
					buf.append(output);
				}
				
				conn.disconnect();
				message = buf.toString();

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}

	

}
