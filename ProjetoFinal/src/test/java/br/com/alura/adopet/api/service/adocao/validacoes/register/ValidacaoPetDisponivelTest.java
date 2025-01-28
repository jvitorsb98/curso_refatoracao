package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a validação de pet disponível para adoção.
 */
public class ValidacaoPetDisponivelTest {

    @InjectMocks
    private ValidacaoPetDisponivel validacaoPetDisponivel;

    @Mock
    private PetRepository petRepository;

    private SolicitacaoAdocaoDTO solicitacaoAdocaoDTO;
    private Pet petDisponivel;
    private Pet petAdotado;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e objetos necessários
        MockitoAnnotations.openMocks(this);

        solicitacaoAdocaoDTO = new SolicitacaoAdocaoDTO(1L, 1L, "Quero adotar!");

        petDisponivel = new Pet();
        petDisponivel.setAdotado(false);

        petAdotado = new Pet();
        petAdotado.setAdotado(true);
    }

    @Test
    public void deveValidarPetDisponivel() {
        // Simula o comportamento do repository para retornar um pet disponível
        when(petRepository.getReferenceById(solicitacaoAdocaoDTO.idPet())).thenReturn(petDisponivel);

        // Chama o método de validação
        validacaoPetDisponivel.validar(solicitacaoAdocaoDTO);

        // Verifica que não há exceções lançadas, ou seja, o pet está disponível
        verify(petRepository, times(1)).getReferenceById(solicitacaoAdocaoDTO.idPet());
    }

    @Test
    public void naoDeveValidarPetAdotado() {
        // Simula o comportamento do repository para retornar um pet já adotado
        when(petRepository.getReferenceById(solicitacaoAdocaoDTO.idPet())).thenReturn(petAdotado);

        // Verifica que uma exceção é lançada
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            validacaoPetDisponivel.validar(solicitacaoAdocaoDTO);
        });

        // Verifica a mensagem da exceção
        assertEquals("Pet já foi adotado", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoSePetNaoExistir() {
        // Simula o comportamento do repository caso o pet não exista (exemplo: pet não encontrado)
        when(petRepository.getReferenceById(solicitacaoAdocaoDTO.idPet())).thenThrow(new RuntimeException("Pet não encontrado"));

        // Verifica que uma exceção é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            validacaoPetDisponivel.validar(solicitacaoAdocaoDTO);
        });

        // Verifica a mensagem da exceção
        assertEquals("Pet não encontrado", exception.getMessage());
    }


}
