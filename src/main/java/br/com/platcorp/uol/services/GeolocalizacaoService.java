package br.com.platcorp.uol.services;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	private GeolocalizacaoDTO findLocation(String ip) {
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
	private String findDistance(String latitude, String longitude) {

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
	private ClimaDTO findClima(String woeid, String dataUSA) {

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
	
	
	/**
	 *  ESSE METÓDO É O PRINCIPAL COMO PUBLICO NESSA CLASSE DE SERVIÇO;
	 *  AQUI FAÇO A CHAMADA DOS MÉTODOS NA SEQUENCIA CORRETA, POIS AS APIs DEPENDE DE VALORES NA SEQUENCIA.
	 *  
	 *  1 - PEGO O IP EXTERNO ATRAVES DA CLASSE
	 *  2 - EXECUTO METODO findLocation() ONDE ME RETORNA UM OBJETO QUE CONTEM A LATITUDE E LONGITUDE (https://ipvigilante.com)
	 *  3 - COM A (LATITUDE E LONGITUDE) CHAMO OUTRO METODO QUE FAZ REQUISIÇÃO EM OUTRA API https://www.metaweather.com/api/location/search/?lattlong=latitude,longitude";
	 *  4 - COM RESULTADO DO PASSO ANTERIOR PEGO CODIGO '(woeid) mais a data do dia' e passo para API https://www.metaweather.com/api/woeid/dia/mes/ano/
	 *  
	 * @return
	 */
	
	public ClimaDTO buscaTemperatura() {
		
		GeolocalizacaoDTO geolocalizacaoDTO = findLocation(Rede.pegarIP());
		
		String woeid = this.findDistance(geolocalizacaoDTO.getData().getLatitude(), geolocalizacaoDTO.getData().getLongitude());

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String dataUSA = df.format(new Date());
		
		ClimaDTO climaDTO = this.findClima(woeid, dataUSA);
		
		return climaDTO;
	}
	

	
	
	public static void main(String[] args) {
		GeolocalizacaoService service = new GeolocalizacaoService();
		

		ClimaDTO clima = service.buscaTemperatura();
		
		System.out.println( clima.getMinTemp() );
		System.out.println( clima.getMaxTemp() );
		
		
		
		
	}
	
	
	
}
