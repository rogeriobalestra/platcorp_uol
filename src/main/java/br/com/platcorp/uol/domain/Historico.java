package br.com.platcorp.uol.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer idCliente;
	private String ipOrigem;
	private String latitude;
	private String longitude;
	private String temperaturaMaxima;
	private String temperaturaMinima;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dtInsercao;

	
	//Construtor
	public Historico() {
		
	}
	
	//Construtor
	public Historico(Integer id, Integer idCliente, String ipOrigem, String latitude, String longitude,
			String temperaturaMaxima, String temperaturaMinima, Date dtInsercao) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.ipOrigem = ipOrigem;
		this.latitude = latitude;
		this.longitude = longitude;
		this.temperaturaMaxima = temperaturaMaxima;
		this.temperaturaMinima = temperaturaMinima;
		this.dtInsercao = dtInsercao;
	}

	
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpOrigem() {
		return ipOrigem;
	}

	public void setIpOrigem(String ipOrigem) {
		this.ipOrigem = ipOrigem;
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

	public String getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(String temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public String getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(String temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public Date getDtInsercao() {
		return dtInsercao;
	}

	public void setDtInsercao(Date dtInsercao) {
		this.dtInsercao = dtInsercao;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	
	
	

	
	
}
