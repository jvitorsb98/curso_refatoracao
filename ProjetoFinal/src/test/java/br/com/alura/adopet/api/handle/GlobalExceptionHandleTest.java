package br.com.alura.adopet.api.handle;

import br.com.alura.adopet.api.validations.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandleTest {

    @InjectMocks
    private GlobalExceptionHandle globalExceptionHandle;

    private ValidacaoException validacaoException;
    private Exception genericException;

    @BeforeEach
    void setUp() {
        // Criação de exceções de exemplo
        validacaoException = new ValidacaoException("Erro de validação");
        genericException = new Exception("Erro genérico");
    }

    @Test
    void testAdocaoException() {
        // Chama o método de tratamento da exceção
        ResponseEntity<ResponseError> responseEntity = globalExceptionHandle.adocaoException(validacaoException);

        // Verifica se a resposta tem o status correto e a mensagem esperada
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Erro de validação", responseEntity.getBody().message());
    }

    @Test
    void testTrataException() {
        // Chama o método de tratamento para exceção genérica
        ResponseEntity<ResponseError> responseEntity = globalExceptionHandle.trataException(genericException);

        // Verifica se a resposta tem o status correto e a mensagem esperada
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Erro genérico", responseEntity.getBody().message());
    }
}
