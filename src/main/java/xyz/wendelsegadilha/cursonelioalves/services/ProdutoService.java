package xyz.wendelsegadilha.cursonelioalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import xyz.wendelsegadilha.cursonelioalves.domain.Categoria;
import xyz.wendelsegadilha.cursonelioalves.domain.Produto;
import xyz.wendelsegadilha.cursonelioalves.repositories.CategoriaRepository;
import xyz.wendelsegadilha.cursonelioalves.repositories.ProdutoRepository;
import xyz.wendelsegadilha.cursonelioalves.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer size, String direction, String order) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), order);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
		
	}

}
