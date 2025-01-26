package br.com.alura.adopet.validacoes;


import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoPetDisponivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@DisplayName("Teste da classe ValidacaoPetDisponivel")
@ExtendWith(MockitoExtension.class)
public class ValidacaoPetDisponivelTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private Pet pet;

    @InjectMocks
    private ValidacaoPetDisponivel validacaoPetDisponivel;

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoPEt(){

        //ARRANGE
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(
                7l,
                2l,
                "Motivo qualquer"
        );

        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(pet.getAdotado()).willReturn(false);
        //ACT + ASSERT
        Assertions.assertDoesNotThrow( () -> validacaoPetDisponivel.validar(dto));

    }

    @Test
    void naoDeveriaPermitirSolicitacaoDeAdocaoPet(){

        //ARRANGE
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(
                7l,
                2l,
                "Motivo qualquer"
        );

        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(pet.getAdotado()).willReturn(true);
        //ACT + ASSERT
        Assertions.assertThrows(ValidacaoException.class,() -> validacaoPetDisponivel.validar(dto));

    }

}
