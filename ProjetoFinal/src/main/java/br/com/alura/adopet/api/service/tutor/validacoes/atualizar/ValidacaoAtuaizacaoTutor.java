package br.com.alura.adopet.api.service.tutor.validacoes.atualizar;

import br.com.alura.adopet.api.dto.tutor.atualizar.AtualizarTutorDTO;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.validations.ValidacaoException;

/**
 * Interface para validações específicas ao atualizar um {@link Tutor}.
 *
 * <p>Esta interface define o contrato para as validações que devem ser realizadas
 * quando um tutor é atualizado. A implementação dessa interface deverá realizar
 * as verificações necessárias antes de realizar a atualização de um tutor.</p>
 */
public interface ValidacaoAtuaizacaoTutor {

    /**
     * Método responsável por validar as informações de um tutor antes da atualização.
     *
     * @param idTutor ID do tutor que será atualizado.
     * @throws ValidacaoException Se a validação falhar.
     */
    void validacao(Long idTutor);
}
