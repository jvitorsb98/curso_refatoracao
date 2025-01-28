package br.com.alura.adopet.api.service.tutor.validacoes.atualizar;

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
class ValidacaoTutorExistsTest {

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private ValidacaoTutorExists validacaoTutorExists;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks antes de cada teste
    }

    @Test
    void testValidacaoQuandoTutorExiste() {
        // Definindo o ID do tutor
        Long idTutor = 1L;

        // Configurando o mock para retornar true quando verificar a existência do tutor
        when(tutorRepository.existsById(idTutor)).thenReturn(true);

        // Chamando o método de validação
        validacaoTutorExists.validacao(idTutor);

        // Verificando se o método do repositório foi chamado corretamente
        verify(tutorRepository).existsById(idTutor);

        // Nenhuma exceção deve ser lançada
    }

    @Test
    void testValidacaoQuandoTutorNaoExiste() {
        // Definindo o ID do tutor
        Long idTutor = 1L;

        // Configurando o mock para retornar false quando verificar a existência do tutor
        when(tutorRepository.existsById(idTutor)).thenReturn(false);

        // Verificando se a exceção é lançada quando o tutor não existe
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            validacaoTutorExists.validacao(idTutor);
        });

        // Verificando a mensagem da exceção
        assertEquals("Tutor informado para atualização não existe", exception.getMessage());

        // Verificando se o método do repositório foi chamado
        verify(tutorRepository).existsById(idTutor);
    }
}
