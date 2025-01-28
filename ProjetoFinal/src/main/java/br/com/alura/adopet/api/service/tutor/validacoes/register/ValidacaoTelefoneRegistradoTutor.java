package br.com.alura.adopet.api.service.tutor.validacoes.register;

import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validação para garantir que o telefone informado para o registro do tutor
 * não esteja cadastrado em outro tutor.
 *
 * <p>Esta classe implementa a interface {@link ValidacaoRegistroTutor} e
 * realiza a verificação de que o telefone fornecido no registro de um tutor
 * não está presente em outros registros de tutor no banco de dados.</p>
 *
 * <p>Se o telefone já estiver cadastrado, uma exceção {@link ValidacaoException}
 * será lançada com a mensagem apropriada.</p>
 */
@Component
public class ValidacaoTelefoneRegistradoTutor implements ValidacaoRegistroTutor {

    @Autowired
    private TutorRepository tutorRepository;

    /**
     * Valida o telefone do {@link TutorRegistroDTO} para garantir que não
     * esteja registrado em outro tutor.
     *
     * @param tutorRegistroDTO Dados do tutor a serem validados.
     * @throws ValidacaoException Se o telefone já estiver registrado.
     */
    @Override
    public void validation(TutorRegistroDTO tutorRegistroDTO) {
        if (tutorRepository.existsByTelefone(tutorRegistroDTO.telefone())) {
            throw new ValidacaoException("Telefone já cadastrado para outro tutor");
        }
    }
}
