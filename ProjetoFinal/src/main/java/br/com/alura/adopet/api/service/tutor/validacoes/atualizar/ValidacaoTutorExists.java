package br.com.alura.adopet.api.service.tutor.validacoes.atualizar;

import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validação para verificar se o {@link Tutor} informado existe antes de realizar a atualização.
 *
 * <p>Esta classe implementa a interface {@link ValidacaoAtuaizacaoTutor} e realiza uma
 * verificação para garantir que o tutor com o ID fornecido exista no sistema antes de
 * permitir a atualização.</p>
 *
 * @see TutorRepository
 * @see ValidacaoAtuaizacaoTutor
 */
@Component
public class ValidacaoTutorExists implements ValidacaoAtuaizacaoTutor {

    @Autowired
    private TutorRepository tutorRepository;

    /**
     * Verifica se o tutor com o ID fornecido existe no sistema.
     *
     * @param idTutor ID do tutor a ser atualizado.
     * @throws ValidacaoException Se o tutor não existir no sistema.
     */
    @Override
    public void validacao(Long idTutor) {
        if (!tutorRepository.existsById(idTutor)) {
            throw new ValidacaoException("Tutor informado para atualização não existe");
        }
    }
}
