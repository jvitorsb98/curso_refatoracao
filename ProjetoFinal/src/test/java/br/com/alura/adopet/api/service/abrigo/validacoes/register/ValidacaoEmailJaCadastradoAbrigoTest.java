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
@DisplayName("Testes de validação para o cadastro de abrigo com e-mail já cadastrado")
public class ValidacaoEmailJaCadastradoAbrigoTest {

    @Mock
    private AbrigoRepository abrigoRepository;

    @InjectMocks
    private ValidacaoEmailJaCadastradoAbrigo validacaoEmailJaCadastradoAbrigo;

    private AbrigoRegisterDTO abrigoRegisterDTO;

    @BeforeEach
    public void setUp() {
        // Inicializando o DTO com dados de exemplo
        abrigoRegisterDTO = new AbrigoRegisterDTO("Abrigo Teste", "(11) 99999-9999", "teste@abrigo.com");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail já estiver cadastrado")
    public void testValidacaoEmailJaCadastrado_QuandoEmailJaExistir() {
        // Arrange: Simulando que o e-mail já existe no banco
        when(abrigoRepository.existsByEmail(abrigoRegisterDTO.email())).thenReturn(true);

        // Act & Assert: Validando que a exceção será lançada com a mensagem esperada
        ValidacaoException thrown = assertThrows(ValidacaoException.class, () -> {
            validacaoEmailJaCadastradoAbrigo.validation(abrigoRegisterDTO);
        });

        assertEquals("Email já cadastrado para outro abrigo", thrown.getMessage());
        verify(abrigoRepository, times(1)).existsByEmail(abrigoRegisterDTO.email());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando o e-mail não estiver cadastrado")
    public void testValidacaoEmailJaCadastrado_QuandoEmailNaoExistir() {
        // Arrange: Simulando que o e-mail não existe no banco
        when(abrigoRepository.existsByEmail(abrigoRegisterDTO.email())).thenReturn(false);

        // Act: A validação não deve lançar exceção
        validacaoEmailJaCadastradoAbrigo.validation(abrigoRegisterDTO);

        // Assert: Verificando que o método existsByEmail foi chamado uma vez
        verify(abrigoRepository, times(1)).existsByEmail(abrigoRegisterDTO.email());
    }
}
