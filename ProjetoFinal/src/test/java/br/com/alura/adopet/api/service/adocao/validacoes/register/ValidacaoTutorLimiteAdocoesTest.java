package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.enums.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a validação do limite de adoções do tutor.
 */
public class ValidacaoTutorLimiteAdocoesTest {

    @InjectMocks
    private ValidacaoTutorLimiteAdocoes validacaoTutorLimiteAdocoes;

    @Mock
    private AdocaoRepository adocaoRepository;

    private SolicitacaoAdocaoDTO solicitacaoAdocaoDTO;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e objetos necessários
        MockitoAnnotations.openMocks(this);

        solicitacaoAdocaoDTO = new SolicitacaoAdocaoDTO(1L, 1L, "Quero adotar!");
    }

    @Test
    public void deveValidarTutorSemLimiteDeAdocoes() {
        // Simula o comportamento do repositório, retornando que o tutor tem menos de 5 adoções aprovadas
        when(adocaoRepository.countByTutorIdAndStatus(solicitacaoAdocaoDTO.idTutor(), StatusAdocao.APROVADO)).thenReturn(4L);

        // Chama o método de validação
        validacaoTutorLimiteAdocoes.validar(solicitacaoAdocaoDTO);

        // Verifica que o método do repositório foi chamado corretamente
        verify(adocaoRepository, times(1)).countByTutorIdAndStatus(solicitacaoAdocaoDTO.idTutor(), StatusAdocao.APROVADO);
    }

    @Test
    public void naoDeveValidarTutorComLimiteDeAdocoes() {
        // Simula o comportamento do repositório, retornando que o tutor já atingiu o limite de 5 adoções aprovadas
        when(adocaoRepository.countByTutorIdAndStatus(solicitacaoAdocaoDTO.idTutor(), StatusAdocao.APROVADO)).thenReturn(5L);

        // Verifica que uma exceção é lançada
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            validacaoTutorLimiteAdocoes.validar(solicitacaoAdocaoDTO);
        });

        // Verifica a mensagem da exceção
        assertEquals("Tutor chegou ao limite máximo de 5 adoções!", exception.getMessage());
    }
}
