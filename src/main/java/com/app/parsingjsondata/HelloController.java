package com.app.parsingjsondata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
	
	@Value("${app.apiKey}")
	private String apiKey;
	@GetMapping("/")
	
	public String getLocationDetails() throws JSONException {
		
		String location = "Bhubaneswar";
		String url = "http://www.mapquestapi.com/geocoding/v1/address?key=" + apiKey + "&location=" + location + "";

		RestTemplate restTemplate = new RestTemplate();

		String response = restTemplate.getForObject(url, String.class);

		System.out.println("response..................... " + response);
		
		JSONObject responseObj  = new JSONObject(response);
		
		JSONArray resultArray = responseObj.getJSONArray("results");
		
		System.out.println("resultArray..................... " + resultArray);

		for (int i = 0; i < resultArray.length(); i++) {
			
			JSONObject resultObj = resultArray.getJSONObject(i);
			
			JSONArray  locationArray  = resultObj.getJSONArray("locations") ;
			
			for (int j = 0; j < locationArray.length(); j++) {
				
				JSONObject locationObj = locationArray.getJSONObject(j);
				
				String adminArea5 = locationObj.getString("adminArea5");
				System.out.println("adminArea5..... "  +  adminArea5);
				String adminArea1 = locationObj.getString("adminArea1");
				
				System.out.println("adminArea1..... "  +  adminArea1);

				
				JSONObject latlonObj = locationObj.getJSONObject("latLng");
				
				double latitude = latlonObj.getDouble("lat");
				
				System.out.println("latitude...... "  +  latitude);
				double longitude = latlonObj.getDouble("lng");
				System.out.println("longitude...... "  +  longitude);
				
			}
			
		}
		
		return response;
		
		
	}

}
