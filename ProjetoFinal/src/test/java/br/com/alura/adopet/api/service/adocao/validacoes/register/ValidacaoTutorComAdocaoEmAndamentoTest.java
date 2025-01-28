package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.enums.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a validação do tutor com adoção em andamento.
 */
public class ValidacaoTutorComAdocaoEmAndamentoTest {

    @InjectMocks
    private ValidacaoTutorComAdocaoEmAndamento validacaoTutorComAdocaoEmAndamento;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private TutorRepository tutorRepository;

    private SolicitacaoAdocaoDTO solicitacaoAdocaoDTO;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks e objetos necessários
        MockitoAnnotations.openMocks(this);

        solicitacaoAdocaoDTO = new SolicitacaoAdocaoDTO(1L, 1L, "Quero adotar!");
    }

    @Test
    public void deveValidarTutorSemAdocaoEmAndamento() {
        // Simula o comportamento do repositório para verificar que o tutor não possui adoção em andamento
        when(adocaoRepository.existsByTutorIdAndStatus(solicitacaoAdocaoDTO.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)).thenReturn(false);

        // Chama o método de validação
        validacaoTutorComAdocaoEmAndamento.validar(solicitacaoAdocaoDTO);

        // Verifica que o método do repositório foi chamado corretamente
        verify(adocaoRepository, times(1)).existsByTutorIdAndStatus(solicitacaoAdocaoDTO.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);
    }

    @Test
    public void naoDeveValidarTutorComAdocaoEmAndamento() {
        // Simula o comportamento do repositório para verificar que o tutor já possui adoção aguardando avaliação
        when(adocaoRepository.existsByTutorIdAndStatus(solicitacaoAdocaoDTO.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)).thenReturn(true);

        // Verifica que uma exceção é lançada
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            validacaoTutorComAdocaoEmAndamento.validar(solicitacaoAdocaoDTO);
        });

        // Verifica a mensagem da exceção
        assertEquals("Tutor já possui outra adoção aguardando avaliação!", exception.getMessage());
    }
}
