package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.GetShopListInterface;
import com.example.dao.GetShopsList;
import com.example.geo.GeoCodingAPIAccessHelper;
import com.example.util.ApplicationConstants;
import com.example.util.LongLatValidater;
import com.example.vo.PostalCodeResponce;

@RestController
public class GetFullAdress {
 @RequestMapping(value ="/getStoreList", method=RequestMethod.GET)
	
	 
	 public @ResponseBody String registerShop(@RequestParam String longitude,@RequestParam String latitude) {
		 	GeoCodingAPIAccessHelper geoCodingHelper=new GeoCodingAPIAccessHelper();
		 	
		 	/*
		 	 * 
		 	 * Commenting Below code as Reverse geocoding api is giving weird response.
		 	 */
		 	/*//Approch 1: Find the zipcode for provided longitude and latitude and retrive shops registerd with the same zipcode (Same area will have common zipcode)
			PostalCodeResponce postalCodeResponse=geoCodingHelper.getAddressPostalCode(longitude, latitude);
			
			// to do get shos near to this long and lati
			
		 	if (!ApplicationConstants.GEOCODING_RES_OK.equals(postalCodeResponse.getStatus())) {
				 if (ApplicationConstants.GEOCODING_RES_ZERO.equalsIgnoreCase(postalCodeResponse.getStatus()) ) return ApplicationConstants.APIRESPONCE_INVALIDREQUST;
				 if (ApplicationConstants.GEOCODING_RES_INVALIDREQUST.equalsIgnoreCase(postalCodeResponse.getStatus()) ) return ApplicationConstants.APIRESPONCE_INVALIDREQUST;
				 if (ApplicationConstants.GEOCODING_RES_LIMITEXCEEDED.equalsIgnoreCase(postalCodeResponse.getStatus()) ) return ApplicationConstants.APIRESPONCE_LIMITEXCEEDED;
				 if (ApplicationConstants.GEOCODING_RES_REQUESTDENIED.equalsIgnoreCase(postalCodeResponse.getStatus()) ) return ApplicationConstants.APIRESPONCE_REQUESTDENIED;
				 if (ApplicationConstants.GEOCODING_RES_UNKNOWN_ERROR.equalsIgnoreCase(postalCodeResponse.getStatus()) ) return ApplicationConstants.APIRESPONCE_UNKNOWN_ERROR;
			 }
		 	
		 	GetShopListInterface getShopList=new GetShopsList();
		 	String shopsListJsonString=getShopList.getShopsList(postalCodeResponse.getPostalCode());
		 	*/
		 	//getShopsNearPostCode()
		 	
		 	//Approch2 : Get Langitude latitude first 5 chars and compare with existing values in in json
		 
		 	GetShopListInterface getShopList=new GetShopsList();
		 	if (longitude==null || latitude==null) return ApplicationConstants.SEARCH_INVALIDRANGE;
			if (longitude.isEmpty()||latitude.isEmpty()) return ApplicationConstants.SEARCH_INVALIDRANGE;
			if (! LongLatValidater.validateLatitude(latitude)
					|| !LongLatValidater.validateLongitude(longitude))
				return ApplicationConstants.SEARCH_INVALIDRANGE;
			
		 	
		 	String shopsListJsonString=getShopList.getShopsList(longitude,latitude);
		 	System.out.println("Shops list for location "+longitude+" & "+latitude +" is : "+shopsListJsonString);
		 	if (shopsListJsonString!=null)
		 			return shopsListJsonString;
		 	else
		 		return ApplicationConstants.SEARCH_FAILURE;
	 }

}
