package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.enums.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.service.adocao.validacoes.register.ValidacaoPetComAdocaoEmAndamento;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para a validação de pet com adoção em andamento")
public class ValidacaoPetComAdocaoEmAndamentoTest {

    @Mock
    private AdocaoRepository adocaoRepository;

    @InjectMocks
    private ValidacaoPetComAdocaoEmAndamento validacaoPetComAdocaoEmAndamento;

    @Test
    @DisplayName("Deve lançar exceção quando pet estiver aguardando avaliação para adoção")
    public void testValidarPetComAdocaoEmAndamento() {
        // Configurando o DTO de solicitação de adoção
        SolicitacaoAdocaoDTO solicitacaoAdocaoDTO = new SolicitacaoAdocaoDTO(1L, 123L, "Quero adotar esse pet");

        // Simulando que o pet já está aguardando avaliação para adoção
        when(adocaoRepository.existsByPetIdAndStatus(1L, StatusAdocao.AGUARDANDO_AVALIACAO)).thenReturn(true);

        // Executando a validação e verificando se a exceção é lançada
        assertThrows(ValidacaoException.class, () -> validacaoPetComAdocaoEmAndamento.validar(solicitacaoAdocaoDTO),
                "Pet já está aguardando avaliação para ser adotado!");
    }

    @Test
    @DisplayName("Não deve lançar exceção quando pet não estiver aguardando avaliação para adoção")
    public void testValidarPetSemAdocaoEmAndamento() {
        // Configurando o DTO de solicitação de adoção
        SolicitacaoAdocaoDTO solicitacaoAdocaoDTO = new SolicitacaoAdocaoDTO(1L, 123L, "Quero adotar esse pet");

        // Simulando que o pet NÃO está aguardando avaliação para adoção
        when(adocaoRepository.existsByPetIdAndStatus(1L, StatusAdocao.AGUARDANDO_AVALIACAO)).thenReturn(false);

        // Executando a validação e verificando que nenhuma exceção é lançada
        assertDoesNotThrow(() -> validacaoPetComAdocaoEmAndamento.validar(solicitacaoAdocaoDTO));
    }


}
