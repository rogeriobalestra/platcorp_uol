package br.com.platcorp.uol.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.platcorp.uol.domain.Cliente;
import br.com.platcorp.uol.repositories.ClienteRepository;
import br.com.platcorp.uol.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	
	/* Insere Cliente */
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	
	//Busca Cliente por ID
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
			
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrato " + id + " Tipo: " + Cliente.class.getName()));
	}

	
	//Update
	public Cliente update(Cliente cliente) {
		find(cliente.getId());
		return repo.save(cliente);
	}
	
	
	//Delete
	public void delete(Integer id) {
		find(id); //para garantir que esteja vindo algum id no parametro.
		repo.deleteById(id);
	}

	
	public List<Cliente> findAll() {
		return repo.findAll();
	}


}
