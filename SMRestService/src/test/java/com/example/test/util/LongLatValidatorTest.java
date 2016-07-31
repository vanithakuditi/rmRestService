package com.example.test.util;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.util.LongLatValidater;
public class LongLatValidatorTest {
	@Test
	public void longitudeFormatingTest(){
		boolean result=LongLatValidater.validateLongitude("34.45.444444");
		assertEquals(false, result);
	}
	
	@Test
	public void longitudeRangeTest(){
		boolean result=LongLatValidater.validateLongitude("199");
		assertEquals(false, result);
	}
	
	@Test
	public void latitudeFormatingTest(){
		boolean result=LongLatValidater.validateLatitude("34.abcd");
		assertEquals(false, result);
	}
	
	@Test
	public void latitudeRangeTest(){
		boolean result=LongLatValidater.validateLatitude("98");
		assertEquals(false, result);
	}

}
