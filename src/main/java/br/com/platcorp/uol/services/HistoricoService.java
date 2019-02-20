package br.com.platcorp.uol.services;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.platcorp.uol.domain.Historico;
import br.com.platcorp.uol.repositories.HistoricoRepository;
import br.com.platcorp.uol.services.exceptions.ObjectNotFoundException;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository repo;
	
	
	public Historico save(Integer idCliente) {
		Historico historico = new Historico();
		if(idCliente != null) {
			historico.setIdCliente(idCliente);
			historico.setIpOrigem(Rede.pegarIP());
			historico.setDtInsercao(new Date());
			return repo.save(historico);
		} else {
			 throw new ObjectNotFoundException("ID Cliente não encontrado");
		}
	}
	
	
}
