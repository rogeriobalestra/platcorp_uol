package br.com.platcorp.uol.services;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.platcorp.uol.dto.ClimaDTO;
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
	 * @return
	 */
	public GeolocalizacaoDTO buscar(String ip) {
		String url = "https://ipvigilante.com/"+ip; //187.105.147.187 (vem por parametro)
		RestTemplate restTemplate = new RestTemplate();
	    GeolocalizacaoDTO  geolocalizacao = restTemplate.getForObject(url, GeolocalizacaoDTO.class);
	    return geolocalizacao;
	}

	/**
	 * Busca Temperatura
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public ClimaDTO buscaClima(String latitude, String longitude) {
		
		ClimaDTO climaDTO = new ClimaDTO();
		
		return climaDTO;
	}
	
}
