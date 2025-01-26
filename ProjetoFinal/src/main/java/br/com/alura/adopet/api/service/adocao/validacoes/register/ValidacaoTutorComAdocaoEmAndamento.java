package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.enums.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private TutorRepository tutorRepository;

    /**
     * Valida se o tutor já possui uma adoção em andamento aguardando avaliação.
     *
     * @param dto Objeto contendo os dados da solicitação de adoção.
     *
     * @throws ValidacaoException Se o tutor já estiver com outra adoção aguardando avaliação.
     */
    public void validar(SolicitacaoAdocaoDTO dto) {
        // Verifica se o tutor existe e se ele já possui uma adoção aguardando avaliação em uma única consulta
        if (adocaoRepository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)) {
            throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
        }
    }
}
