package br.com.platcorp.uol.repositories;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.platcorp.uol.domain.Cliente;
import br.com.platcorp.uol.services.ClienteService;

@RestController
@RequestMapping(value="clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> cliente(@RequestBody Cliente cliente) {
		
		System.out.println(cliente.getNome());
		System.out.println(cliente.getIdade());
		
		
		Cliente obj = service.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/id").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
	
	
	
}
