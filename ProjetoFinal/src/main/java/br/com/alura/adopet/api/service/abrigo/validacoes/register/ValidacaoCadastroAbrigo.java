package br.com.alura.adopet.api.service.abrigo.validacoes.register;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.stereotype.Component;

/**
 * Interface responsável por definir a validação para o cadastro de um abrigo.
 *
 * <p>Essa interface deve ser implementada por classes que fornecem a lógica de validação
 * para o registro de abrigos, garantindo que os dados fornecidos sejam válidos antes de
 * permitir o cadastro.</p>
 */
@Component
public interface ValidacaoCadastroAbrigo {

    /**
     * Método de validação do cadastro de um abrigo.
     *
     * <p>Este método é responsável por realizar as validações necessárias no objeto {@link AbrigoRegisterDTO}
     * antes de permitir o cadastro do abrigo no sistema.</p>
     *
     * @param abrigoRegisterDTO O objeto DTO contendo os dados do abrigo a ser registrado.
     * @throws ValidacaoException Se algum dado do cadastro for inválido.
     */
    void validation(AbrigoRegisterDTO abrigoRegisterDTO);
}
