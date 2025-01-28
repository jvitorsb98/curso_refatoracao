package br.com.alura.adopet.api.service.adocao.validacoes.aprovar;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidarAdocaoExisteTest {

    @Mock
    private AdocaoRepository adocaoRepository;

    @InjectMocks
    private ValidarAdocaoExisteParaAprovar validarAdocaoExiste;

    @Test
    void deveLancarExcecaoQuandoAdocaoNaoExistir() {
        // Dado que a adoção não existe no repositório
        AprovacaoAdocaoDTO aprovacaoAdocaoDTO = new AprovacaoAdocaoDTO(1L);

        when(adocaoRepository.existsById(aprovacaoAdocaoDTO.idAdocao())).thenReturn(false);

        // Quando a validação for chamada
        // Então deve lançar a exceção ValidacaoException
        assertThrows(ValidacaoException.class, () -> validarAdocaoExiste.validar(aprovacaoAdocaoDTO));
    }

    @Test
    void naoDeveLancarExcecaoQuandoAdocaoExistir() {
        // Dado que a adoção existe no repositório
        AprovacaoAdocaoDTO aprovacaoAdocaoDTO = new AprovacaoAdocaoDTO(1L);

        when(adocaoRepository.existsById(aprovacaoAdocaoDTO.idAdocao())).thenReturn(true);

        // Quando a validação for chamada
        // Não deve lançar exceção
        assertDoesNotThrow(() -> validarAdocaoExiste.validar(aprovacaoAdocaoDTO));
    }
}
