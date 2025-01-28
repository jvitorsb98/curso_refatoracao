package br.com.alura.adopet.api.service.adocao.validacoes.reprovar;

import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.service.adocao.validacoes.reprovar.ValidarAdocaoExisteParaReprovar;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ValidarAdocaoExisteParaReprovarTest {

    @Mock
    private AdocaoRepository adocaoRepository;

    @InjectMocks
    private ValidarAdocaoExisteParaReprovar validarAdocaoExisteParaReprovar;

    @Test
    void deveLancarExcecaoQuandoAdocaoNaoExistir() {
        // Dado que a adoção não existe no repositório
        ReprovacaoAdocaoDTO reprovacaoAdocaoDTO = new ReprovacaoAdocaoDTO(1L, "Motivo da reprovação");

        when(adocaoRepository.existsById(reprovacaoAdocaoDTO.idAdocao())).thenReturn(false);

        // Quando a validação for chamada
        // Então deve lançar a exceção ValidacaoException
        assertThrows(ValidacaoException.class, () -> validarAdocaoExisteParaReprovar.validar(reprovacaoAdocaoDTO));
    }

    @Test
    void naoDeveLancarExcecaoQuandoAdocaoExistir() {
        // Dado que a adoção existe no repositório
        ReprovacaoAdocaoDTO reprovacaoAdocaoDTO = new ReprovacaoAdocaoDTO(1L, "Motivo da reprovação");

        when(adocaoRepository.existsById(reprovacaoAdocaoDTO.idAdocao())).thenReturn(true);

        // Quando a validação for chamada
        // Não deve lançar exceção
        assertDoesNotThrow(() -> validarAdocaoExisteParaReprovar.validar(reprovacaoAdocaoDTO));
    }
}
