package br.com.alura.adopet.api.service.adocao.validacoes.aprovar;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDTO;
import org.springframework.stereotype.Component;

@Component
public interface ValidacaoSolicitacaoAprovar {

    void validar(AprovacaoAdocaoDTO aprovacaoAdocaoDTO);

}
