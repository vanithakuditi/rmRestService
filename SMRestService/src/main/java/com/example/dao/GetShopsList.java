package com.example.dao;

import java.io.FileReader;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetShopsList implements GetShopListInterface {
	
	public void getShopsList(String zipCode){
		
	}
	
	public static void main(String[] args) {
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader(
                    "/Users/vkuditi/Documents/file1.txt"));
 
            JSONObject jsonObject = (JSONObject) obj;
 
            String name = (String) jsonObject.get("Name");
            String author = (String) jsonObject.get("Author");
            JSONArray companyList = (JSONArray) jsonObject.get("Company List");
 
            System.out.println("Name: " + name);
            System.out.println("Author: " + author);
           
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
