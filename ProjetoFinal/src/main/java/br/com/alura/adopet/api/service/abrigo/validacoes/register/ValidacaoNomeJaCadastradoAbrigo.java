package br.com.alura.adopet.api.service.abrigo.validacoes.register;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por validar se o nome informado para o cadastro de um abrigo já está cadastrado.
 *
 * <p>Essa classe verifica se o nome fornecido no DTO de registro já foi utilizado em outro abrigo.
 * Se o nome já estiver cadastrado, uma exceção {@link ValidacaoException} será lançada.</p>
 *
 * @see ValidacaoCadastroAbrigo
 */
@Component
public class ValidacaoNomeJaCadastradoAbrigo implements ValidacaoCadastroAbrigo {

    @Autowired
    private AbrigoRepository abrigoRepository;

    /**
     * Valida se o nome fornecido no DTO de registro já está cadastrado.
     *
     * <p>Se o nome já estiver associado a outro abrigo, uma {@link ValidacaoException} será lançada
     * com a mensagem informando que o nome já está cadastrado.</p>
     *
     * @param abrigoRegisterDTO O objeto DTO contendo os dados do abrigo a ser registrado, incluindo o nome.
     * @throws ValidacaoException Se o nome já estiver cadastrado para outro abrigo.
     */
    @Override
    public void validation(AbrigoRegisterDTO abrigoRegisterDTO) {
        if (abrigoRepository.existsByNome(abrigoRegisterDTO.nome())) {
            throw new ValidacaoException("Nome já cadastrado para outro abrigo");
        }
    }
}
