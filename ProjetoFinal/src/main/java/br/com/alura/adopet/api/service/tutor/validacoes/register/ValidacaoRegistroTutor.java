package br.com.alura.adopet.api.service.tutor.validacoes.register;

import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.model.Tutor;
import org.springframework.stereotype.Component;

/**
 * Interface para validações no processo de registro de um {@link Tutor}.
 *
 * <p>Esta interface define o contrato para as classes responsáveis por realizar
 * as validações durante o processo de registro de um tutor. Cada implementação
 * da interface deve fornecer uma validação específica para os dados do tutor
 * durante o registro.</p>
 *
 * @see TutorRegistroDTO
 */
@Component
public interface ValidacaoRegistroTutor {

    /**
     * Realiza as validações necessárias no {@link TutorRegistroDTO} durante o
     * processo de registro de um tutor.
     *
     * @param tutorRegistroDTO Dados do tutor a serem validados.
     */
    void validation(TutorRegistroDTO tutorRegistroDTO);

}
