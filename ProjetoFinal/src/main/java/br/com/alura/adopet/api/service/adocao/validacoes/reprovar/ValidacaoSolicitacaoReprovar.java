package br.com.alura.adopet.api.service.adocao.validacoes.reprovar;

import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDTO;
import org.springframework.stereotype.Component;

@Component
public interface ValidacaoSolicitacaoReprovar {

    void validar(ReprovacaoAdocaoDTO reprovacaoAdocaoDTO);
}
