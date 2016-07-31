package com.example.test.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.example.geo.GeoCodingAPIAccessHelper;
import com.example.vo.ShopsVO;

public class GeoCodingApiAccessHelperTest {
	@Test
	public void nullValuesTest(){
		GeoCodingAPIAccessHelper geoCoding=new GeoCodingAPIAccessHelper();
		
		ShopsVO shop=new ShopsVO();
		shop.setShopName(null);
		shop.setShopNumber(null);
		shop.setPostalCode(null);
		
		ShopsVO responceObject=geoCoding.getAddressWithLongitudeLatitude(shop);
		
		assertNotNull(responceObject);
		//System.out.println(responceObject.getStatus());
		assertEquals("ZERO_RESULTS", responceObject.getStatus());
		
	}
	
	@Test
	public void ValuesTest(){
		GeoCodingAPIAccessHelper geoCoding=new GeoCodingAPIAccessHelper();
		
		ShopsVO shop=new ShopsVO();
		shop.setShopName("Citadel");
		shop.setShopNumber("d3");
		shop.setPostalCode("411001");
		
		ShopsVO responceObject=geoCoding.getAddressWithLongitudeLatitude(shop);
		
		assertNotNull(responceObject);
		//System.out.println(responceObject.getStatus());
		assertEquals("OK", responceObject.getStatus());
		
	}
}
