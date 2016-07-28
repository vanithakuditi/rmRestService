/**
 * 
 */
package com.example.geo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.example.json.mappings.GeoCodingLongLatitudes;
import com.example.json.mappings.GeoCodingResponse;
import com.example.util.ApplicationConstants;
import com.example.vo.ShopsVO;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vkuditi
 *
 */
public class GeoCodingAPIAccessHelper {
	
	@SuppressWarnings("deprecation")
	public ShopsVO getAddressWithLongitudeLatitude(ShopsVO shop){
		StringBuffer address=new StringBuffer();
		ShopsVO shopVo = new ShopsVO();
		StringBuilder url = new StringBuilder("http://maps.googleapis.com/maps/api/geocode/json?");
		  
	    url.append("sensor=false&address=");
	    
	    address.append(shop.getShopName());
		address.append(",");
		address.append(shop.getShopNumber());
		address.append(",");
		address.append(shop.getPostalCode());
		System.out.println("Adress is : "+address);
	    url.append( URLEncoder.encode(address.toString()) );
	  
	    // request url like: http://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(address) + "&sensor=false"
	    // do request
	    try (CloseableHttpClient httpclient = HttpClients.createDefault();) {
	        HttpGet request = new HttpGet(url.toString());

	        // set common headers (may useless)
	        request.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:31.0) Gecko/20100101 Firefox/31.0 Iceweasel/31.6.0");
	        request.setHeader("Host", "maps.googleapis.com");
	        request.setHeader("Connection", "keep-alive");
	        request.setHeader("Accept-Language", "en-US,en;q=0.5");
	       // request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        request.setHeader("Accept-Encoding", "gzip, deflate");

	        try (CloseableHttpResponse response = httpclient.execute(request)) {
	            org.apache.http.HttpEntity entity = response.getEntity();
	            System.out.println("Responce is +"+response);
	            // recover String response (for debug purposes)
	            StringBuilder result = new StringBuilder();
	            try (BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()))) {
	                String inputLine;
	                while ((inputLine = in.readLine()) != null) {
	                    result.append(inputLine);
	                    result.append("\n");
	                }
	               System.out.println("Result is :"+result);
	            }catch (Exception e) {
					// TODO: handle exception
				}
	        

	            // parse result
	            ObjectMapper mapper = new ObjectMapper();
	            GeoCodingResponse geoCodeRes = mapper.readValue(result.toString(), GeoCodingResponse.class);

	            if (!ApplicationConstants.GEOCODING_RES_OK.equals(geoCodeRes.getStatus())) {
	               shopVo.setStatus(geoCodeRes.getStatus());
	               return shopVo;
	            }
	         
	            shopVo.setStatus(geoCodeRes.getStatus()); 
	           GeoCodingLongLatitudes longLatitudes= geoCodeRes.getResults()[0].getGeometry().getLocation();
	           shopVo.setLatitude(longLatitudes.getLat());
	           shopVo.setLongitude(longLatitudes.getLng());
	           shopVo.setShopName(shop.getShopName());
	           shopVo.setShopNumber(shop.getShopNumber());
	           shopVo.setPostalCode(shop.getPostalCode());
	           System.out.println("Longitude : "+shopVo.getLongitude());
	        }catch (Exception e) {
				shopVo.setStatus(ApplicationConstants.APIRESPONCE_UNKNOWN_ERROR);
			}
	    }catch (Exception e) {
	    	shopVo.setStatus(ApplicationConstants.APIRESPONCE_UNKNOWN_ERROR);
		}


		return shopVo;
	}
	
	public void getAddress(String longitude,String latitude){
		System.out.println("Logitude is "+longitude +" and Latitude is : +"+latitude);
		String latlang="&latlng="+longitude+","+latitude;
		 StringBuilder url = new StringBuilder("http");
		  
		    url.append("://maps.googleapis.com/maps/api/geocode/json?");
		    url.append(latlang);
		    //url.append("sensor=false&address=");
		   // url.append( URLEncoder.encode(address) );
		  
		    // request url like: http://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(address) + "&sensor=false"
		    // do request
		    try (CloseableHttpClient httpclient = HttpClients.createDefault();) {
		        HttpGet request = new HttpGet(url.toString());

		        // set common headers (may useless)
		        request.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:31.0) Gecko/20100101 Firefox/31.0 Iceweasel/31.6.0");
		        request.setHeader("Host", "maps.googleapis.com");
		        request.setHeader("Connection", "keep-alive");
		        request.setHeader("Accept-Language", "en-US,en;q=0.5");
		       // request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		        request.setHeader("Accept-Encoding", "gzip, deflate");

		        try (CloseableHttpResponse response = httpclient.execute(request)) {
		        	org.apache.http.HttpEntity  entity = response.getEntity();
		            System.out.println("Responce is +"+response);
		            // recover String response (for debug purposes)
		            StringBuilder result = new StringBuilder();
		            try (BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()))) {
		                String inputLine;
		                while ((inputLine = in.readLine()) != null) {
		                    result.append(inputLine);
		                    result.append("\n");
		                }
		               System.out.println("Result is :"+result);
		            }catch (Exception e) {
						// TODO: handle exception
					}
		        

		            // parse result
		          //  ObjectMapper mapper = new ObjectMapper();
		          //  GoogleGeoCode geocode = mapper.readValue(result.toString(), GoogleGeoCode.class);

		            /*if (!"OK".equals(geocode.getStatus())) {
		                if (geocode.getError_message() != null) {
		                    throw new Exception(geocode.getError_message());
		                }
		                throw new Exception("Can not find geocode for: " + address);
		            }
		            return geocode;*/
		        }catch (Exception e) {
					// TODO: handle exception
				}
		    }catch (Exception e) {
				// TODO: handle exception
			}

	}

}
