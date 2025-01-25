package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;

public interface ValidacaoSolicitacaoAdocao {

    void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO);

}
