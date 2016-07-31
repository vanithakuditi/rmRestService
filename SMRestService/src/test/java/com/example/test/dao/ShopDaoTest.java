package com.example.test.dao;

import org.junit.Test;

import com.example.dao.SaveDataDao;
import com.example.dao.SaveDataDaoInterface;
import com.example.vo.ShopsVO;
import static org.junit.Assert.*;


public class ShopDaoTest {
	@Test
	public void testSaveDaoNullTest(){
		SaveDataDaoInterface savedataDao=new SaveDataDao();
		
		ShopsVO shopTest1=new ShopsVO();
		shopTest1.setLatitude(null);
		shopTest1.setLongitude("34.000000");
		shopTest1.setPostalCode("123456");
		shopTest1.setShopName("ABCDEF");
		shopTest1.setShopNumber("123");
		
		boolean result=savedataDao.saveShopInfo(shopTest1);
		assertEquals(false,result);
	}
	
	@Test
	public void testSaveDaoEmptyTest(){
		SaveDataDaoInterface savedataDao=new SaveDataDao();
		
		ShopsVO shopTest1=new ShopsVO();
		shopTest1.setLatitude("45.565777");
		shopTest1.setLongitude("34.000000");
		shopTest1.setPostalCode("123456");
		shopTest1.setShopName("");
		shopTest1.setShopNumber("123");
		
		boolean result=savedataDao.saveShopInfo(shopTest1);
		assertEquals(false,result);
	}
	
	@Test
	public void testSaveDaoInValidLonglatTest(){
		SaveDataDaoInterface savedataDao=new SaveDataDao();
		
		ShopsVO shopTest1=new ShopsVO();
		shopTest1.setLatitude("45.5657.77");
		shopTest1.setLongitude("34.000000");
		shopTest1.setPostalCode("123456");
		shopTest1.setShopName("ABB");
		shopTest1.setShopNumber("123");
		
		boolean result=savedataDao.saveShopInfo(shopTest1);
		assertEquals(false,result);
	}
	
	@Test
	public void testSaveDaoTest(){
		SaveDataDaoInterface savedataDao=new SaveDataDao();
		
		ShopsVO shopTest1=new ShopsVO();
		shopTest1.setLatitude("45.565777");
		shopTest1.setLongitude("34.000000");
		shopTest1.setPostalCode("123456");
		shopTest1.setShopName("ABB");
		shopTest1.setShopNumber("123");
		
		boolean result=savedataDao.saveShopInfo(shopTest1);
		assertEquals(true,result);
	}


}
