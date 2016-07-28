package com.example.json.mappings;

public class GeoCodingGeometry {
	private GeoCodingBoundaries bounds;
    private GeoCodingLongLatitudes location;
    private String location_type;
    private GeoCodingBoundaries viewport;
	/**
	 * @return the bounds
	 */
	public GeoCodingBoundaries getBounds() {
		return bounds;
	}
	/**
	 * @param bounds the bounds to set
	 */
	public void setBounds(GeoCodingBoundaries bounds) {
		this.bounds = bounds;
	}
	/**
	 * @return the location
	 */
	public GeoCodingLongLatitudes getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(GeoCodingLongLatitudes location) {
		this.location = location;
	}
	/**
	 * @return the location_type
	 */
	public String getLocation_type() {
		return location_type;
	}
	/**
	 * @param location_type the location_type to set
	 */
	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}
	/**
	 * @return the viewport
	 */
	public GeoCodingBoundaries getViewport() {
		return viewport;
	}
	/**
	 * @param viewport the viewport to set
	 */
	public void setViewport(GeoCodingBoundaries viewport) {
		this.viewport = viewport;
	}
    
    
}
