package com.example.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetShopsList implements GetShopListInterface {
	
	public String getShopsList(String longitude,String latitude){
		//String shopsJsonArrayString="";
		
		try{
			JSONArray responceJsonArray=new JSONArray();
			
			if (longitude==null || latitude==null) return null;
			if (longitude.isEmpty()||latitude.isEmpty()) return null;
			
			String lngShortForm=longitude.substring(0,3);
			String latitudeShortForm=latitude.substring(0,3);
			 StringBuffer sb = new StringBuffer();
		     BufferedReader br = null;
		     try {
		    	 System.out.println("Before reading file");
		    	 	br = new BufferedReader(new FileReader("Shops.json"));
		    	 System.out.println("After reading file");
		         String temp;
		         while ((temp = br.readLine()) != null)
		             sb.append(temp);
		     } catch (IOException e) {
		    	 return null;
		       
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
				    
				     for (int i=0;i<shopsArray.length();i++){
				    	 JSONObject temp=shopsArray.getJSONObject(i);
				    	String  tempLat=temp.getString("latitude");
				    	String  tempLng=temp.getString("longitude");
				    	 if (tempLat.contains(latitudeShortForm)&&tempLng.contains(lngShortForm))
				    	 {
				    		 responceJsonArray.put(temp);
				    	 }
					  }
				     System.out.println("Response Json is :"+responceJsonArray.toString());
				     
			    }catch(Exception e){
			    	 return null;
			    }
    
	     	return responceJsonArray.toString();
		}catch(Exception e){
			return null;
		}
	}
	
	public String getShopsList(String zipCode){
		//String shopsJsonArrayString="";
		
		try{
			JSONArray responceJsonArray=new JSONArray();
			
			if (zipCode==null) return null;
			if (zipCode.isEmpty()) return null;
			 StringBuffer sb = new StringBuffer();
		     BufferedReader br = null;
		     try {
		    	 System.out.println("Before reading file");
		    	 	br = new BufferedReader(new FileReader("Shops.json"));
		    	 System.out.println("After reading file");
		         String temp;
		         while ((temp = br.readLine()) != null)
		             sb.append(temp);
		     } catch (IOException e) {
		    	 return null;
		       
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
				    
				     for (int i=0;i<shopsArray.length();i++){
				    	 JSONObject temp=shopsArray.getJSONObject(i);
				    	 System.out.println(temp.get("postalCode"));
				    	 if (zipCode.equals(temp.get("postalCode"))){
				    		 //add to Json Array
				    		 responceJsonArray.put(temp);
				    	 }
					  }
				     System.out.println("Response Json is :"+responceJsonArray.toString());
				     
			    }catch(Exception e){
			    	 return null;
			    }
    
	     	return responceJsonArray.toString();
		}catch(Exception e){
			return null;
		}
	}

	
	public static void main(String[] args) {
		GetShopsList getShopList=new GetShopsList();
		System.out.println(getShopList.getShopsList("73.90675519999999", "18.5203044"));
		System.out.println(getShopList.getShopsList("4110036"));
	}

}
