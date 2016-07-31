package com.example.test.dao;

import org.junit.Test;

import com.example.dao.GetShopListInterface;
import com.example.dao.GetShopsList;
import static org.junit.Assert.*;

public class GetShopsListTest {
	@Test
	public void invalidValueTest(){
		GetShopListInterface getShopsList=new GetShopsList();
		String longitude="";
		String Latitude="34.234234234";
		String result=getShopsList.getShopsList(longitude,Latitude);
		assertNull(result);
	}
	
	@Test
	public void invalidFormatTest(){
		GetShopListInterface getShopsList=new GetShopsList();
		String longitude="asdasd";
		String Latitude="34.234234234";
		String result=getShopsList.getShopsList(longitude,Latitude);
		assertNull(result);
	}
	@Test
	public void invalidRangeTest(){
		GetShopListInterface getShopsList=new GetShopsList();
		String longitude="190.32423432";
		String Latitude="34.234234234";
		String result=getShopsList.getShopsList(longitude,Latitude);
		assertNull(result);
	}
	
	@Test
	public void shopsNotFoundTest(){
		GetShopListInterface getShopsList=new GetShopsList();
		//Bay Of Bengal coordinates
		String longitude="9.968851";
		String Latitude="86.660156";
		String result=getShopsList.getShopsList(longitude,Latitude);
		assertEquals("No Shops Found near specified location", result);
	}
}
