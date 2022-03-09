package xyz.wendelsegadilha.cursonelioalves.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import xyz.wendelsegadilha.cursonelioalves.domain.PagamentoComBoleto;

/*
 * Simulação de serviço de geração de boletos
 */
@Service
public class BoletoService {
	
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

}
