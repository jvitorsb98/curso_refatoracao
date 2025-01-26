package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.enums.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorLimiteAdocoes implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    /**
     * Valida se o tutor atingiu o limite de adoções aprovadas.
     *
     * @param dto Objeto contendo os dados da solicitação de adoção.
     *
     * @throws ValidacaoException Se o tutor já tiver atingido o limite de 5 adoções aprovadas.
     */
    public void validar(SolicitacaoAdocaoDTO dto) {
        long totalAdoçõesAprovadas = adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO);

        if (totalAdoçõesAprovadas >= 5) {
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
}
