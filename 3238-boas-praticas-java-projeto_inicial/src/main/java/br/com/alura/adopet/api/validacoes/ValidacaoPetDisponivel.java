package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao{

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO){
        Pet pet = petRepository.getReferenceById(solicitacaoAdocaoDTO.idPet());
            if(pet.getAdotado()){
                throw new ValidacaoException("Pet ja foi adotado");
            }

    }

}
