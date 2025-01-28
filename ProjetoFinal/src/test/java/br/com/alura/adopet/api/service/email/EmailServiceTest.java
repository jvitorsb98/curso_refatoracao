package br.com.alura.adopet.api.service.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.mockito.Mockito.verify;

class EmailServiceTest {

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendEmail() {
        // Definindo os parâmetros para o teste
        String to = "test@example.com";
        String subject = "Teste de Assunto";
        String message = "Conteúdo do e-mail de teste.";

        // Chamando o método que envia o e-mail
        emailService.send(to, subject, message);

        // Verificando se o método send() foi chamado uma vez com o e-mail configurado corretamente
        SimpleMailMessage expectedEmail = new SimpleMailMessage();
        expectedEmail.setFrom("adopet@email.com.br");
        expectedEmail.setTo(to);
        expectedEmail.setSubject(subject);
        expectedEmail.setText(message);

        verify(emailSender).send(expectedEmail);
    }
}
