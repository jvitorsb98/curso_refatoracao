package br.com.alura.adopet.api.service.adocao.validacoes.aprovar;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarAdocaoExisteParaAprovar implements ValidacaoSolicitacaoAprovar{

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Override
    public void validar(AprovacaoAdocaoDTO aprovacaoAdocaoDTO) {
        if(!adocaoRepository.existsById(aprovacaoAdocaoDTO.idAdocao())){
            throw new ValidacaoException("Adocão não encontrada");
        }
    }
}
