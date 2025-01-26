package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {


    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO){
        if(adocaoRepository.existsByPetIdAndStatus(solicitacaoAdocaoDTO.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO)){
            throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }

}
