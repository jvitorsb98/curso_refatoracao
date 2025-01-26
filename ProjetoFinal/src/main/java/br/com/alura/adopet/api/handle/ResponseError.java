package br.com.alura.adopet.api.handle;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Classe record responsável por encapsular os detalhes de erros em respostas HTTP.
 * Utilizada para padronizar os dados retornados em caso de exceções tratadas globalmente.
 *
 * @param message    Mensagem de erro descritiva.
 * @param httpStatus Código de status HTTP associado ao erro.
 * @param now        Data e hora em que o erro ocorreu.
 */
public record ResponseError(String message, HttpStatus httpStatus, LocalDateTime now) {
}
