package com.example;


import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SmRestServiceApplication.class)
@WebAppConfiguration
public class SmRestServiceApplicationTests {
	//Test RestTemplate to invoke the APIs.
	  private RestTemplate restTemplate = new TestRestTemplate();
	  		
	/*
	 * RestAPI: Api for registering a shop
	 * 
	 * Requset URL : http://vkuditi-8ukzx:8080/registerStore
	 * 
	 * Method: Post
	 * 
	 * Content :Application/JSON
	 * 
	 * Json Format:
	 * { 
     *   "shopName":"XXXXXXXX",
     *   "shopNumber":"XXXXXX",
     *   "postalCode":"XXXXXXXXX"
     *  }
	 * 
	 */
	@Test
	public void testRegisterShopsAPI() throws JSONException{
		 //Building the Request body data
		  JSONObject requestBody=new JSONObject();
		  requestBody.put("shopName", "Citadel");
		  requestBody.put("shopNumber", "d3");
		  requestBody.put("postalCode", "411001");
		 
		  HttpHeaders requestHeaders = new HttpHeaders();
		  requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		  //Creating http entity object with request body and headers
		  HttpEntity<String> httpEntity =new HttpEntity<String>(requestBody.toString(), requestHeaders);

		  //Invoking the API
		  String response=restTemplate.postForObject("http://localhost:8080/registerStore", httpEntity,String.class);
		
		  assertNotNull(response);
		  
		  assertEquals("Successfully saved Shop information. Thanks for Registering", response);
		
		  
	}
	@Test
	public void testGetShopsAPI() throws JSONException{
		 
		  //Invoking the API
		  String response=restTemplate.getForObject("http://localhost:8080/getStoreList?longitude=73.90675519999999&latitude=18.5203044",String.class);
		
		  assertNotNull(response);
		  
		  JSONArray shopList=new JSONArray(response);
		  
		   for (int i=0;i<shopList.length();i++){
		    	 JSONObject temp=shopList.getJSONObject(i);
		    	String  tempLat=temp.getString("latitude");
		    	String  tempLng=temp.getString("longitude");
		    	assertEquals("73.90",tempLng.substring(0, 5));
		    	assertEquals("18.52",tempLat.substring(0, 5));
		 	  }
	}
	
	@Test
	public void testGetShopswithInvalidInputsAPI() throws JSONException{
		 
		  //Invoking the API
		  String response=restTemplate.getForObject("http://localhost:8080/getStoreList?longitude=189&latitude=99",String.class);
		
		  assertNotNull(response);
		  
		 assertEquals("Invalid Longitude LatitudeValues. Please enter vlaid values in range (-180 to 180)for"
		 		+ " longitude and (-90 to 90)for latitude"
				 , response);
	}

}
