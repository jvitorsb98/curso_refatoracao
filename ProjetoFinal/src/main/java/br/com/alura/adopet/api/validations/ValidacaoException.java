package br.com.alura.adopet.api.validations;

/**
 * Exceção personalizada para indicar erros de validação durante a execução do sistema.
 *
 * <p>Essa exceção é lançada quando ocorre uma falha de validação nos dados ou na lógica de negócios
 * e serve para encapsular a mensagem de erro associada à falha de validação.</p>
 */
public class ValidacaoException extends RuntimeException {

    /**
     * Constrói uma nova exceção {@link ValidacaoException} com a mensagem fornecida.
     *
     * @param message A mensagem de erro associada à exceção.
     */
    public ValidacaoException(String message){
        super(message);
    }
}
