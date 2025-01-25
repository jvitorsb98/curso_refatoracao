package br.com.alura.adopet.api.service.email;

import br.com.alura.adopet.api.model.Adocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {

    @Autowired
    private JavaMailSender emailSender;


    public void send(String to , String subject , String message){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(to);
        email.setSubject(message);
        email.setText(message);
        emailSender.send(email);
    }

}
