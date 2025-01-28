package br.com.alura.adopet.api.service.adocao.validacoes.reprovar;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarAdocaoExisteParaReprovar implements ValidacaoSolicitacaoReprovar{

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Override
    public void validar(ReprovacaoAdocaoDTO reprovacaoAdocaoDTO) {
        if(!adocaoRepository.existsById(reprovacaoAdocaoDTO.idAdocao())){
            throw new ValidacaoException("Adocão não encontrada");
        }
    }

}
