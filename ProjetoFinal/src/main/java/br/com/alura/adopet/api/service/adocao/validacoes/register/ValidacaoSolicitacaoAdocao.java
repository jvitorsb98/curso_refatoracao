package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.validations.ValidacaoException;

/**
 * Interface responsável por definir as regras de validação para uma solicitação
 * de adoção.
 *
 * <p>Qualquer implementação dessa interface será responsável por validar os dados
 * contidos no objeto {@link SolicitacaoAdocaoDTO} antes que uma solicitação de adoção
 * seja processada ou registrada no sistema.</p>
 */
public interface ValidacaoSolicitacaoAdocao {

    /**
     * Realiza as validações necessárias para a solicitação de adoção.
     *
     * @param solicitacaoAdocaoDTO Objeto contendo os dados da solicitação de adoção
     *                             a ser validada.
     *
     * @throws ValidacaoException Se houver erro na validação, uma exceção é lançada.
     */
    void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO);
}
