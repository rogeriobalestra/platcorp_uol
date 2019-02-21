package br.com.platcorp.uol.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.platcorp.uol.domain.Cliente;
import br.com.platcorp.uol.domain.Historico;
import br.com.platcorp.uol.services.ClienteService;
import br.com.platcorp.uol.services.GeolocalizacaoService;
import br.com.platcorp.uol.services.HistoricoService;


@RestController
@RequestMapping(value="/api/v1/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@Autowired
	private HistoricoService historicoService;
	
	@Autowired
	private GeolocalizacaoService geolocalizacaoService;
	
	
	/**
	 * LIST ALL CLIENTS
	 * @return
	 */
	@GetMapping
	public List<Cliente> findAll() {
		return service.findAll();
	}
	
	/**
	 * INSERT
	 * @param cliente
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Cliente cliente) {
		
		Cliente obj = service.insert(cliente);
		
		historicoService.save(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/id").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * SEARCH FOR ID
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente cliente = service.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	
	/**
	 * DELETE
	 * @param cliente
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Integer id){
		if(cliente.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		service.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	
	
	/**
	 * DELETE
	 * obs: Também pode ser usado da Forma antiga, deixei de exemplo
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	
	
			
			
}
