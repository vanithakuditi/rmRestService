package com.example.json.mappings;

public class GeoCodingResponse {
	private String status;
    private GeoCodingResults [] results;
    private Boolean exclude_from_slo;
    private String error_message;
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the results
	 */
	public GeoCodingResults[] getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(GeoCodingResults[] results) {
		this.results = results;
	}
	/**
	 * @return the exclude_from_slo
	 */
	public Boolean getExclude_from_slo() {
		return exclude_from_slo;
	}
	/**
	 * @param exclude_from_slo the exclude_from_slo to set
	 */
	public void setExclude_from_slo(Boolean exclude_from_slo) {
		this.exclude_from_slo = exclude_from_slo;
	}
	/**
	 * @return the error_message
	 */
	public String getError_message() {
		return error_message;
	}
	/**
	 * @param error_message the error_message to set
	 */
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
    
    
}
