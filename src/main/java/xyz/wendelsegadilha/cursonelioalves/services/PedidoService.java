package xyz.wendelsegadilha.cursonelioalves.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.wendelsegadilha.cursonelioalves.domain.Pedido;
import xyz.wendelsegadilha.cursonelioalves.repositories.PedidoRepository;
import xyz.wendelsegadilha.cursonelioalves.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(
					() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())
				);
	}
}
