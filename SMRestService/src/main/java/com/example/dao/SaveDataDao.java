package com.example.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.util.LongLatValidater;
import com.example.vo.ShopsVO;

public class SaveDataDao  implements SaveDataDaoInterface{
public boolean saveShopInfo(ShopsVO shopInfo){
	//	System.out.println("Start of saving shop info for : "+ shopInfo.getShopName());
	if (shopInfo.getLatitude()==null || shopInfo.getLongitude()==null || shopInfo.getPostalCode()==null ||
			shopInfo.getShopName()==null || shopInfo.getShopNumber()==null)
		return false;
	else if (shopInfo.getLatitude().isEmpty() || shopInfo.getLongitude().isEmpty() || shopInfo.getPostalCode().isEmpty() ||
			shopInfo.getShopName().isEmpty() || shopInfo.getShopNumber().isEmpty())
		return false;
	
	if (!LongLatValidater.validateLatitude(shopInfo.getLatitude()) ||
			!LongLatValidater.validateLongitude(shopInfo.getLongitude())	)
			return false;
	
	 StringBuffer sb = new StringBuffer();
     BufferedReader br = null;
     try {
         br = new BufferedReader(new FileReader("Shops.json"));

         String temp;
         while ((temp = br.readLine()) != null)
             sb.append(temp);
     } catch (IOException e) {
    	 return false;
       
     } finally {
         try {
             br.close(); // stop reading
         } catch (IOException e) {
             e.printStackTrace();
             
         }
     }           
     String shopsJsonString = sb.toString();
     System.out.println("shopsJsonString"+shopsJsonString);
     try{
     JSONObject shopsJsonObject=new JSONObject(shopsJsonString);
     JSONArray shopsArray=shopsJsonObject.getJSONArray("shops");
     
     /*
      * Condition to check if address is already exists in shops list 
      * 
      * as this impacts performance commenting.
      *  This logic can be better implemented using database procedure(back end to store address info)
      */
   /*  for (int i=0;i<shopsArray.length();i++){
	 	JSONObject temp= shopsArray.getJSONObject(i);
	 	if (temp.getString("longitude").equals(shopInfo.getLongitude()) &&
	 			temp.getString("latitude").equals(shopInfo.getLatitude()))
	 		return false;
	 }*/
     
     JSONObject newShop=new JSONObject();
     newShop.put("shopName",shopInfo.getShopName());
     newShop.put("shopNumber",shopInfo.getShopNumber());
     newShop.put("postalCode",shopInfo.getPostalCode());
     newShop.put("longitude",shopInfo.getLongitude());
     newShop.put("latitude",shopInfo.getLatitude());
     shopsArray.put(newShop);
     
   
     BufferedWriter writer=new BufferedWriter(new FileWriter("Shops.json"));
     writer.write(shopsJsonObject.toString());
     
     writer.flush();
     writer.close();
     }catch(Exception e){
    	 return false;
     }
     
    return true; 
}

public static void main(String args[]){
	SaveDataDao sdao=new SaveDataDao();
	ShopsVO shopsVO=new ShopsVO();
	shopsVO.setLatitude("45.3434343");
	shopsVO.setLongitude( "56.3434343");
	shopsVO.setPostalCode("411036");
	shopsVO.setShopName("CitadelEnclave");
	shopsVO.setShopNumber("d3");
	sdao.saveShopInfo(shopsVO);
}
}
