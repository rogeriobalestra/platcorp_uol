package br.com.platcorp.uol.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;


//Class Principal
public class GeolocalizacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String status;
	private Data data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//  Criei essa Subclass pra poder ler o Sub objeto dentro do JSON, mais poderia criar um outro DTO externo
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Sub Class 
	public class Data {

		private String ipv4;
				
		@JsonAlias("continent_name")		
		private String continentName;
		
		@JsonAlias("country_name")
		private String countryName;
		
		@JsonAlias("subdivision_1_name")
		private String subdivision1Name;
		
		@JsonAlias("subdivision_2_name")
		private String subdivision2Name;
		
		@JsonAlias("city_name")
		private String cityName;

		private String latitude;

		private String longitude;

		
		
		//GETS AND SETTERS
		public String getIpv4() {
			return ipv4;
		}

		public void setIpv4(String ipv4) {
			this.ipv4 = ipv4;
		}

		public String getContinentName() {
			return continentName;
		}

		public void setContinentName(String continentName) {
			this.continentName = continentName;
		}

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public String getSubdivision1Name() {
			return subdivision1Name;
		}

		public void setSubdivision1Name(String subdivision1Name) {
			this.subdivision1Name = subdivision1Name;
		}

		public String getSubdivision2Name() {
			return subdivision2Name;
		}

		public void setSubdivision2Name(String subdivision2Name) {
			this.subdivision2Name = subdivision2Name;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

	}

}
