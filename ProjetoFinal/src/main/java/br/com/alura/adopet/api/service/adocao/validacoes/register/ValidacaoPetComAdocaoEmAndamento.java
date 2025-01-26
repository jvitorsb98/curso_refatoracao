package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.enums.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validação para garantir que um pet não esteja com uma adoção em andamento.
 *
 * <p>Essa validação verifica se o pet especificado na solicitação de adoção já está
 * aguardando avaliação para ser adotado. Se o pet já está com uma solicitação de adoção
 * em andamento, a solicitação atual é rejeitada.</p>
 *
 * <p>A exceção {@link ValidacaoException} será lançada caso o pet já esteja com uma
 * adoção em andamento no status {@link StatusAdocao#AGUARDANDO_AVALIACAO}.</p>
 */
@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    /**
     * Verifica se o pet especificado na solicitação de adoção já está com uma adoção
     * em andamento.
     *
     * @param solicitacaoAdocaoDTO Objeto contendo os dados da solicitação de adoção,
     *                             incluindo o ID do pet.
     *
     * @throws ValidacaoException Se o pet já estiver aguardando avaliação para ser
     *                            adotado, uma exceção será lançada.
     */
    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) {
        if (adocaoRepository.existsByPetIdAndStatus(solicitacaoAdocaoDTO.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO)) {
            throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
