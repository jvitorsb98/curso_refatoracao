package br.com.alura.adopet.api.service.email;

import br.com.alura.adopet.api.model.Adocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pelo envio de e-mails.
 *
 * <p>Esta classe permite o envio de e-mails simples utilizando o {@link JavaMailSender}. O e-mail é configurado
 * com o remetente, destinatário, assunto e corpo da mensagem, sendo enviado através do serviço de e-mail.</p>
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * Envia um e-mail com os dados fornecidos.
     *
     * <p>Este método permite o envio de um e-mail simples, com o remetente fixo como "adopet@email.com.br",
     * e o assunto e corpo definidos pelos parâmetros fornecidos.</p>
     *
     * @param to O destinatário do e-mail.
     * @param subject O assunto do e-mail.
     * @param message O corpo do e-mail.
     */
    public void send(String to, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);
        emailSender.send(email);
    }
}
