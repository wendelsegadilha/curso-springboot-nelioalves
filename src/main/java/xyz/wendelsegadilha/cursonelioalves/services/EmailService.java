package xyz.wendelsegadilha.cursonelioalves.services;

import org.springframework.mail.SimpleMailMessage;

import xyz.wendelsegadilha.cursonelioalves.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);

}
