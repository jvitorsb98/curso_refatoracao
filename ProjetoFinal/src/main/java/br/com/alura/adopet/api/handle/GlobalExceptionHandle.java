package br.com.alura.adopet.api.handle;

import br.com.alura.adopet.api.validations.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Classe responsável por tratar exceções globais na aplicação.
 * Todas as exceções tratadas nesta classe serão interceptadas automaticamente em toda a aplicação.
 */
@RestControllerAdvice
public class GlobalExceptionHandle {

    /**
     * Trata exceções do tipo {@link ValidacaoException}.
     * Essas exceções geralmente indicam erros de validação em operações específicas.
     *
     * @param e Exceção capturada.
     * @return Uma {@link ResponseEntity} contendo os detalhes do erro.
     */
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ResponseError> adocaoException(ValidacaoException e) {
        ResponseError response = new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Trata exceções genéricas não específicas.
     * Serve como fallback para capturar erros não mapeados por outros handlers.
     *
     * @param e Exceção capturada.
     * @return Uma {@link ResponseEntity} contendo os detalhes do erro.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> trataException(Exception e) {
        ResponseError response = new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
