package br.com.platcorp.uol.services;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.platcorp.uol.domain.Historico;
import br.com.platcorp.uol.dto.ClimaDTO;
import br.com.platcorp.uol.dto.DistanceDTO;
import br.com.platcorp.uol.dto.GeolocalizacaoDTO;

@Service
public class GeolocalizacaoService {

	//
	/**
	 * 
	 * OBS: Essa classe que retorna tem uma subclasse chamada (Data) que é um sub-objeto do JSON.
	 * Para acessar um parametro; Ex: geolocalizacao.getData().getLatitude(); 
	 * 
	 * @param ip
	 * @return Objeto que contem Latitude e Longitude
	 */
	public GeolocalizacaoDTO findLocation(String ip) {
		String url = "https://ipvigilante.com/"+ip; //187.105.147.187 (vem por parametro)
		RestTemplate restTemplate = new RestTemplate();
	    GeolocalizacaoDTO  geolocalizacao = restTemplate.getForObject(url, GeolocalizacaoDTO.class);
	    return geolocalizacao;
	}

	/**
	 * Método responsável por pegar a distancia das cidades
	 * onde conforme passo latitude/longitude e recupero o (woeid) variavel com código para buscar
	 * na api de temperatura esse código junto com a data do dia para retornar temperatura Max e Min.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public String findDistance(String latitude, String longitude) {

		String woeid = null;
		
		String url = "https://www.metaweather.com/api/location/search/?lattlong="+latitude+","+longitude+" ";
		RestTemplate restTemplate = new RestTemplate();
		
		//Json é um Array então tive que capturar desse forma convertendo a classe num array de objeto
		DistanceDTO[] array = restTemplate.getForObject(url, DistanceDTO[].class);
		
		//Caso não achar a primeira cidade pego a mais próxima
		if(array[0].getDistance() == null) {
			woeid = array[0].getWoeid();
		} else {
			woeid = array[0].getWoeid();
		}
		
		//Aqui no final só retorno 1 objeto que preciso e não o array todo
		return woeid;
	}
	
	/**
	 * 
	 * @param woeid (código) para buscar os dados referente teperatura do dia na API
	 * @param dataUSA com barra ex: Ano/mes/dia  (mascara do java = yyyy/MM/dd)
	 * @return
	 */
	public ClimaDTO findClima(String woeid, String dataUSA) {

		//A data deve ser no formato americano ano/mes/dia e com barra e não tracinho
		
		String url = "https://www.metaweather.com/api/location/"+woeid+"/" +dataUSA+ "/";
		RestTemplate restTemplate = new RestTemplate();
		
		//Json é um Array então tive que capturar desse forma convertendo a classe num array de objeto
		ClimaDTO[] array = restTemplate.getForObject(url, ClimaDTO[].class);

		//Pego somente primeiro resulta do array de objeto e populo a classe para pegar a temperatura Max e Min
		ClimaDTO climaDTO = new ClimaDTO();
		climaDTO = array[0];

		//Aqui no final só retorno 1 objeto que preciso e não o array todo
		return climaDTO;
	}
	
	

	
	
	
}
