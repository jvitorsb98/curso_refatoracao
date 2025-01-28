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
class ValidacaoEmailRegistradoTutorTest {

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private ValidacaoEmailRegistradoTutor validacaoEmailRegistradoTutor;

    private TutorRegistroDTO tutorRegistroDTO;

    @BeforeEach
    void setUp() {
        // Inicializa o DTO com um exemplo de email
        tutorRegistroDTO = new TutorRegistroDTO(
                "João Silva",           // Nome do tutor
                "(11)98765-4321",       // Telefone do tutor
                "email@exemplo.com"     // E-mail do tutor
        );
    }

    @Test
    void testValidationQuandoEmailNaoRegistrado() {
        // Configura o mock para retornar false quando verificar a existência do email
        when(tutorRepository.existsByEmail(tutorRegistroDTO.email())).thenReturn(false);

        // Chama o método de validação
        validacaoEmailRegistradoTutor.validation(tutorRegistroDTO);

        // Verifica se o método do repositório foi chamado corretamente
        verify(tutorRepository).existsByEmail(tutorRegistroDTO.email());

        // Nenhuma exceção deve ser lançada
    }

    @Test
    void testValidationQuandoEmailJaRegistrado() {
        // Configura o mock para retornar true quando verificar a existência do email
        when(tutorRepository.existsByEmail(tutorRegistroDTO.email())).thenReturn(true);

        // Verifica se a exceção é lançada quando o email já está registrado
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            validacaoEmailRegistradoTutor.validation(tutorRegistroDTO);
        });

        // Verifica a mensagem da exceção
        assertEquals("Email já cadastrado para outro tutor", exception.getMessage());

        // Verifica se o método do repositório foi chamado corretamente
        verify(tutorRepository).existsByEmail(tutorRegistroDTO.email());
    }
}
