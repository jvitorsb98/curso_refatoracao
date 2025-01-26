package br.com.alura.adopet.api.service.abrigo.validacoes.register;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por validar se o telefone informado para o cadastro de um abrigo já está cadastrado.
 *
 * <p>Essa classe verifica se o telefone fornecido no DTO de registro já foi utilizado em outro abrigo.
 * Se o telefone já estiver cadastrado, uma exceção {@link ValidacaoException} será lançada.</p>
 *
 * @see ValidacaoCadastroAbrigo
 */
@Component
public class ValidacaoTelefoneJaCadastradoAbrigo implements ValidacaoCadastroAbrigo {

    @Autowired
    private AbrigoRepository abrigoRepository;

    /**
     * Valida se o telefone fornecido no DTO de registro já está cadastrado.
     *
     * <p>Se o telefone já estiver associado a outro abrigo, uma {@link ValidacaoException} será lançada
     * com a mensagem informando que o telefone já está cadastrado.</p>
     *
     * @param abrigoRegisterDTO O objeto DTO contendo os dados do abrigo a ser registrado, incluindo o telefone.
     * @throws ValidacaoException Se o telefone já estiver cadastrado para outro abrigo.
     */
    @Override
    public void validation(AbrigoRegisterDTO abrigoRegisterDTO) {
        if (abrigoRepository.existsByTelefone(abrigoRegisterDTO.telefone())) {
            throw new ValidacaoException("Telefone já cadastrado para outro abrigo");
        }
    }
}
