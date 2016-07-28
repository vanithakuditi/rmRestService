/**
 * 
 */
package com.example.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.dao.SaveDataDao;
import com.example.dao.SaveDataDaoInterface;
import com.example.geo.GeoCodingAPIAccessHelper;
import com.example.util.ApplicationConstants;
import com.example.vo.ShopsVO;

/**
 * @author vkuditi
 *
 */

@RestController

public class StoreRegistration {
	
	 @RequestMapping(value ="/registerStore", method=RequestMethod.POST)
	
	 
	 public @ResponseBody String registerShop(@RequestBody ShopsVO shop, UriComponentsBuilder ubc) {
		 	StringBuffer address=new StringBuffer();
		 	
			GeoCodingAPIAccessHelper geoCodingHelper=new GeoCodingAPIAccessHelper();
			ShopsVO shopFullAdress=geoCodingHelper.getAddressWithLongitudeLatitude(shop);
			
			 if (!ApplicationConstants.GEOCODING_RES_OK.equals(shopFullAdress.getStatus())) {
				 if (ApplicationConstants.GEOCODING_RES_ZERO.equalsIgnoreCase(shopFullAdress.getStatus()) ) return ApplicationConstants.APIRESPONCE_INVALIDADDRESS;
				 if (ApplicationConstants.GEOCODING_RES_INVALIDREQUST.equalsIgnoreCase(shopFullAdress.getStatus()) ) return ApplicationConstants.APIRESPONCE_INVALIDREQUST;
				 if (ApplicationConstants.GEOCODING_RES_LIMITEXCEEDED.equalsIgnoreCase(shopFullAdress.getStatus()) ) return ApplicationConstants.APIRESPONCE_LIMITEXCEEDED;
				 if (ApplicationConstants.GEOCODING_RES_REQUESTDENIED.equalsIgnoreCase(shopFullAdress.getStatus()) ) return ApplicationConstants.APIRESPONCE_REQUESTDENIED;
				 if (ApplicationConstants.GEOCODING_RES_UNKNOWN_ERROR.equalsIgnoreCase(shopFullAdress.getStatus()) ) return ApplicationConstants.APIRESPONCE_UNKNOWN_ERROR;
			 }
			// to do save address to in memory
			System.out.println("Latitude "+shopFullAdress.getLatitude());
		 	 SaveDataDaoInterface saveStore=new SaveDataDao();
		 	 boolean dataSaveStatus=saveStore.saveShopInfo(shopFullAdress);
		 	
		 	if (dataSaveStatus) return ApplicationConstants.REGISTRATION_SUCCESS;
		 	else return ApplicationConstants.REGISTRATION_FAILURE;
	 }


}
