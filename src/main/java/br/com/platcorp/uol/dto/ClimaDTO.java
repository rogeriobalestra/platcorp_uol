package br.com.platcorp.uol.dto;

import com.fasterxml.jackson.annotation.JsonAlias;


public class ClimaDTO {

	private String woeid;

	@JsonAlias("min_temp")
	private String minTemp;

	@JsonAlias("max_temp")
	private String maxTemp;

	
	
	public String getWoeid() {
		return woeid;
	}

	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}

	public String getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}

	public String getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}

}
