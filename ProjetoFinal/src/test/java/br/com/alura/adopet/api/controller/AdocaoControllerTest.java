package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.service.adocao.AdocaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdocaoControllerTest {

    @InjectMocks
    private AdocaoController adocaoController;

    @Mock
    private AdocaoService adocaoService;

    private SolicitacaoAdocaoDTO solicitacaoAdocaoDTO;
    private AprovacaoAdocaoDTO aprovacaoAdocaoDTO;
    private ReprovacaoAdocaoDTO reprovacaoAdocaoDTO;

    @BeforeEach
    void setUp() {
        solicitacaoAdocaoDTO = new SolicitacaoAdocaoDTO(1L, 1L, "Motivo");
        aprovacaoAdocaoDTO = new AprovacaoAdocaoDTO(1L);
        reprovacaoAdocaoDTO = new ReprovacaoAdocaoDTO(1L, "Motivo da reprovação");
    }

    @Test
    @DisplayName("Solicitar adoção com sucesso")
    void testSolicitar() {
        // Configura o comportamento do serviço
        doNothing().when(adocaoService).solicitar(solicitacaoAdocaoDTO);

        // Chama o método de solicitação
        ResponseEntity<String> response = adocaoController.solicitar(solicitacaoAdocaoDTO);

        // Verifica se a resposta está OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(adocaoService, times(1)).solicitar(solicitacaoAdocaoDTO);
    }

    @Test
    @DisplayName("Aprovar adoção com sucesso")
    void testAprovar() {
        // Configura o comportamento do serviço
        doNothing().when(adocaoService).aprovar(aprovacaoAdocaoDTO);

        // Chama o método de aprovação
        ResponseEntity<String> response = adocaoController.aprovar(aprovacaoAdocaoDTO);

        // Verifica se a resposta está OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(adocaoService, times(1)).aprovar(aprovacaoAdocaoDTO);
    }

    @Test
    @DisplayName("Reprovar adoção com sucesso")
    void testReprovar() {
        // Configura o comportamento do serviço
        doNothing().when(adocaoService).reprovar(reprovacaoAdocaoDTO);

        // Chama o método de reprovação
        ResponseEntity<String> response = adocaoController.reprovar(reprovacaoAdocaoDTO);

        // Verifica se a resposta está OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(adocaoService, times(1)).reprovar(reprovacaoAdocaoDTO);
    }
}
