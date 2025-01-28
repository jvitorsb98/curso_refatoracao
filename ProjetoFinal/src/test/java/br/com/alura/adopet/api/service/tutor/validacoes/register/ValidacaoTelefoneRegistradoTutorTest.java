package br.com.alura.adopet.api.service.tutor.validacoes.register;

import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoTelefoneRegistradoTutorTest {

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private ValidacaoTelefoneRegistradoTutor validacaoTelefoneRegistradoTutor;

    private TutorRegistroDTO tutorRegistroDTO;

    @BeforeEach
    void setUp() {
        // Inicializa o DTO com exemplo de dados
        tutorRegistroDTO = new TutorRegistroDTO("João", "(11)98765-4321", "email@exemplo.com");
    }

    @Test
    void testValidationTelefoneJaCadastrado() {
        // Configurando o mock para simular que o telefone já existe no banco
        when(tutorRepository.existsByTelefone(tutorRegistroDTO.telefone())).thenReturn(true);

        // Verificando se a exceção é lançada quando o telefone já está cadastrado
        ValidacaoException exception = assertThrows(ValidacaoException.class,
                () -> validacaoTelefoneRegistradoTutor.validation(tutorRegistroDTO));

        // Verificando se a mensagem da exceção é a esperada
        assertEquals("Telefone já cadastrado para outro tutor", exception.getMessage());

        // Verificando se o repositório foi chamado corretamente
        verify(tutorRepository).existsByTelefone(tutorRegistroDTO.telefone());
    }

    @Test
    void testValidationTelefoneNaoCadastrado() {
        // Configurando o mock para simular que o telefone não existe no banco
        when(tutorRepository.existsByTelefone(tutorRegistroDTO.telefone())).thenReturn(false);

        // Chamando o método de validação e garantindo que nenhuma exceção seja lançada
        assertDoesNotThrow(() -> validacaoTelefoneRegistradoTutor.validation(tutorRegistroDTO));

        // Verificando se o repositório foi chamado corretamente
        verify(tutorRepository).existsByTelefone(tutorRegistroDTO.telefone());
    }
}
