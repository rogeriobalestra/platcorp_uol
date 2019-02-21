package br.com.platcorp.uol.services;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.platcorp.uol.domain.Cliente;
import br.com.platcorp.uol.domain.Historico;
import br.com.platcorp.uol.dto.ClimaDTO;
import br.com.platcorp.uol.dto.GeolocalizacaoDTO;
import br.com.platcorp.uol.repositories.HistoricoRepository;
import br.com.platcorp.uol.services.exceptions.ObjectNotFoundException;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository repo;
	
	@Autowired
	private GeolocalizacaoService geolocalizacaoService;
	

	/**
	 * Save History
	 * @param cliente
	 * @return
	 */
	public Historico save(Cliente cliente) {
		
		//Populo objeto com os dados daa Geolocalizacao
		Historico historico = this.buscaDadosTemperaturaMontaHistorico();

		if(cliente != null) {
			historico.setCliente(cliente);;
			return repo.save(historico);
		} else {
			 throw new ObjectNotFoundException("ID Cliente não encontrado");
		}

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
	
	public Historico buscaDadosTemperaturaMontaHistorico() {
		
		String ipOrigem = Rede.pegarIP();
		
		GeolocalizacaoDTO geolocalizacaoDTO = geolocalizacaoService.findLocation(ipOrigem);
		
		String woeid = geolocalizacaoService.findDistance(geolocalizacaoDTO.getData().getLatitude(), geolocalizacaoDTO.getData().getLongitude());

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String dataUSA = df.format(new Date());
		
		ClimaDTO climaDTO = geolocalizacaoService.findClima(woeid, dataUSA);
		
		Historico historico = new Historico();
		historico.setIpOrigem(ipOrigem);
		historico.setLatitude(geolocalizacaoDTO.getData().getLatitude());
		historico.setLongitude(geolocalizacaoDTO.getData().getLongitude());
		historico.setWoeid(woeid);
		historico.setTemperaturaMinima(climaDTO.getMinTemp());
		historico.setTemperaturaMaxima(climaDTO.getMaxTemp());
		historico.setDtInsercao(new Date());
		
		return historico;
	}
	
	
	
	
}
