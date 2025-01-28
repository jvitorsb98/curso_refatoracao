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
@DisplayName("Testes de validação para o cadastro de abrigo com telefone já cadastrado")
public class ValidacaoTelefoneJaCadastradoAbrigoTest {

    @Mock
    private AbrigoRepository abrigoRepository;

    @InjectMocks
    private ValidacaoTelefoneJaCadastradoAbrigo validacaoTelefoneJaCadastradoAbrigo;

    private AbrigoRegisterDTO abrigoRegisterDTO;

    @BeforeEach
    public void setUp() {
        // Inicializando o DTO com dados de exemplo
        abrigoRegisterDTO = new AbrigoRegisterDTO("Abrigo Teste", "(11) 99999-9999", "teste@abrigo.com");
    }

    @Test
    @DisplayName("Deve lançar exceção quando o telefone já estiver cadastrado")
    public void testValidacaoTelefoneJaCadastrado_QuandoTelefoneJaExistir() {
        // Arrange: Simulando que o telefone já existe no banco
        when(abrigoRepository.existsByTelefone(abrigoRegisterDTO.telefone())).thenReturn(true);

        // Act & Assert: Validando que a exceção será lançada com a mensagem esperada
        ValidacaoException thrown = assertThrows(ValidacaoException.class, () -> {
            validacaoTelefoneJaCadastradoAbrigo.validation(abrigoRegisterDTO);
        });

        assertEquals("Telefone já cadastrado para outro abrigo", thrown.getMessage());
        verify(abrigoRepository, times(1)).existsByTelefone(abrigoRegisterDTO.telefone());
    }

    @Test
    @DisplayName("Não deve lançar exceção quando o telefone não estiver cadastrado")
    public void testValidacaoTelefoneJaCadastrado_QuandoTelefoneNaoExistir() {
        // Arrange: Simulando que o telefone não existe no banco
        when(abrigoRepository.existsByTelefone(abrigoRegisterDTO.telefone())).thenReturn(false);

        // Act: A validação não deve lançar exceção
        validacaoTelefoneJaCadastradoAbrigo.validation(abrigoRegisterDTO);

        // Assert: Verificando que o método existsByTelefone foi chamado uma vez
        verify(abrigoRepository, times(1)).existsByTelefone(abrigoRegisterDTO.telefone());
    }
}
