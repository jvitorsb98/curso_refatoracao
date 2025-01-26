package br.com.alura.adopet.api.service.abrigo.validacoes.register;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por validar se o e-mail informado para o cadastro de um abrigo já está cadastrado.
 *
 * <p>Essa classe verifica se o e-mail fornecido no DTO de registro já foi utilizado em outro abrigo.
 * Se o e-mail já estiver cadastrado, uma exceção {@link ValidacaoException} será lançada.</p>
 *
 * @see ValidacaoCadastroAbrigo
 */
@Component
public class ValidacaoEmailJaCadastradoAbrigo implements ValidacaoCadastroAbrigo {

    @Autowired
    private AbrigoRepository abrigoRepository;

    /**
     * Valida se o e-mail fornecido no DTO de registro já está cadastrado.
     *
     * <p>Se o e-mail já estiver associado a outro abrigo, uma {@link ValidacaoException} será lançada
     * com a mensagem informando que o e-mail já está cadastrado.</p>
     *
     * @param abrigoRegisterDTO O objeto DTO contendo os dados do abrigo a ser registrado, incluindo o e-mail.
     * @throws ValidacaoException Se o e-mail já estiver cadastrado para outro abrigo.
     */
    @Override
    public void validation(AbrigoRegisterDTO abrigoRegisterDTO) {
        if (abrigoRepository.existsByEmail(abrigoRegisterDTO.email())) {
            throw new ValidacaoException("Email já cadastrado para outro abrigo");
        }
    }
}
