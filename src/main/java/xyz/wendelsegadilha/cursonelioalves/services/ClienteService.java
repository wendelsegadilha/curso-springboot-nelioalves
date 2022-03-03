package xyz.wendelsegadilha.cursonelioalves.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.wendelsegadilha.cursonelioalves.domain.Cliente;
import xyz.wendelsegadilha.cursonelioalves.repositories.ClienteRepository;
import xyz.wendelsegadilha.cursonelioalves.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(
					() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())
				);
	}
}
