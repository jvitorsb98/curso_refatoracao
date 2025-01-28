package br.com.alura.adopet.api.service.abrigo.validacoes.register;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes de validação para o cadastro de abrigo com nome já cadastrado")
public class ValidacaoNomeJaCadastradoAbrigoTest {

    @Mock
    private AbrigoRepository abrigoRepository;

    @InjectMocks
    private ValidacaoNomeJaCadastradoAbrigo validacaoNomeJaCadastradoAbrigo;

    private AbrigoRegisterDTO abrigoRegisterDTO;

    @BeforeEach
    public void setUp() {
        // Inicializando o DTO com dados de exemplo
        abrigoRegisterDTO = new AbrigoRegisterDTO("Abrigo Teste", "(11) 99999-9999", "teste@abrigo.com");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome já estiver cadastrado")
    public void testValidacaoNomeJaCadastrado_QuandoNomeJaExistir() {
        // Arrange: Simulando que o nome já existe no banco
        when(abrigoRepository.existsByNome(abrigoRegisterDTO.nome())).thenReturn(true);

        // Act & Assert: Validando que a exceção será lançada com a mensagem esperada
        ValidacaoException thrown = assertThrows(ValidacaoException.class, () -> {
            validacaoNomeJaCadastradoAbrigo.validation(abrigoRegisterDTO);
        });

        assertEquals("Nome já cadastrado para outro abrigo", thrown.getMessage());
        verify(abrigoRepository, times(1)).existsByNome(abrigoRegisterDTO.nome());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando o nome não estiver cadastrado")
    public void testValidacaoNomeJaCadastrado_QuandoNomeNaoExistir() {
        // Arrange: Simulando que o nome não existe no banco
        when(abrigoRepository.existsByNome(abrigoRegisterDTO.nome())).thenReturn(false);

        // Act: A validação não deve lançar exceção
        validacaoNomeJaCadastradoAbrigo.validation(abrigoRegisterDTO);

        // Assert: Verificando que o método existsByNome foi chamado uma vez
        verify(abrigoRepository, times(1)).existsByNome(abrigoRegisterDTO.nome());
    }
}
