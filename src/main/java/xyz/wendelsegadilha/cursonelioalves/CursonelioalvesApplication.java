package xyz.wendelsegadilha.cursonelioalves;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xyz.wendelsegadilha.cursonelioalves.domain.Categoria;
import xyz.wendelsegadilha.cursonelioalves.domain.Cidade;
import xyz.wendelsegadilha.cursonelioalves.domain.Cliente;
import xyz.wendelsegadilha.cursonelioalves.domain.Endereco;
import xyz.wendelsegadilha.cursonelioalves.domain.Estado;
import xyz.wendelsegadilha.cursonelioalves.domain.Pagamento;
import xyz.wendelsegadilha.cursonelioalves.domain.PagamentoComBoleto;
import xyz.wendelsegadilha.cursonelioalves.domain.PagamentoComCartao;
import xyz.wendelsegadilha.cursonelioalves.domain.Pedido;
import xyz.wendelsegadilha.cursonelioalves.domain.Produto;
import xyz.wendelsegadilha.cursonelioalves.domain.enums.EstadoPagamento;
import xyz.wendelsegadilha.cursonelioalves.domain.enums.TipoCliente;
import xyz.wendelsegadilha.cursonelioalves.repositories.CategoriaRepository;
import xyz.wendelsegadilha.cursonelioalves.repositories.CidadeRepository;
import xyz.wendelsegadilha.cursonelioalves.repositories.ClienteRepository;
import xyz.wendelsegadilha.cursonelioalves.repositories.EnderecoRepository;
import xyz.wendelsegadilha.cursonelioalves.repositories.EstadoRepository;
import xyz.wendelsegadilha.cursonelioalves.repositories.PagamentoRepository;
import xyz.wendelsegadilha.cursonelioalves.repositories.PedidoRepository;
import xyz.wendelsegadilha.cursonelioalves.repositories.ProdutoRepository;

@SpringBootApplication
public class CursonelioalvesApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursonelioalvesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "57467972199", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("8225563890", "82983060193"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "65300855", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "65300855", c2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/110/2017 10:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
