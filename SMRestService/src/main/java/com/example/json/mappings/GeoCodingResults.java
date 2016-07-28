package com.example.json.mappings;

public class GeoCodingResults {
	 private GeoCodingAdressComponent [] address_components;
	    private String formatted_address;
	    private GeoCodingGeometry geometry;
	    private Boolean partial_match;
	    private String place_id;
	    private String [] types;
		/**
		 * @return the address_components
		 */
		public GeoCodingAdressComponent[] getAddress_components() {
			return address_components;
		}
		/**
		 * @param address_components the address_components to set
		 */
		public void setAddress_components(GeoCodingAdressComponent[] address_components) {
			this.address_components = address_components;
		}
		/**
		 * @return the formatted_address
		 */
		public String getFormatted_address() {
			return formatted_address;
		}
		/**
		 * @param formatted_address the formatted_address to set
		 */
		public void setFormatted_address(String formatted_address) {
			this.formatted_address = formatted_address;
		}
		/**
		 * @return the geometry
		 */
		public GeoCodingGeometry getGeometry() {
			return geometry;
		}
		/**
		 * @param geometry the geometry to set
		 */
		public void setGeometry(GeoCodingGeometry geometry) {
			this.geometry = geometry;
		}
		/**
		 * @return the partial_match
		 */
		public Boolean getPartial_match() {
			return partial_match;
		}
		/**
		 * @param partial_match the partial_match to set
		 */
		public void setPartial_match(Boolean partial_match) {
			this.partial_match = partial_match;
		}
		/**
		 * @return the place_id
		 */
		public String getPlace_id() {
			return place_id;
		}
		/**
		 * @param place_id the place_id to set
		 */
		public void setPlace_id(String place_id) {
			this.place_id = place_id;
		}
		/**
		 * @return the types
		 */
		public String[] getTypes() {
			return types;
		}
		/**
		 * @param types the types to set
		 */
		public void setTypes(String[] types) {
			this.types = types;
		}
	    
	    
}
