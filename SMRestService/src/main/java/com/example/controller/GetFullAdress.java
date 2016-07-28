package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.geo.GeoCodingAPIAccessHelper;
import com.example.vo.ShopsVO;

@RestController
public class GetFullAdress {
 @RequestMapping(value ="/getFullAddress", method=RequestMethod.GET)
	
	 
	 public @ResponseBody String registerShop(@RequestParam String longitude,@RequestParam String lotitude) {
		 	GeoCodingAPIAccessHelper geoCodingHelper=new GeoCodingAPIAccessHelper();
			geoCodingHelper.getAddress(longitude, lotitude);
			
			// to do get shos near to this long and lati
			
		 	    
		 	
		 	System.out.println("Hay hi invoking rest api"+longitude+lotitude);
		 	return "abc";
	 }

}
