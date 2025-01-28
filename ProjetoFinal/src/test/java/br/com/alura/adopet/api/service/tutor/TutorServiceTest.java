package br.com.alura.adopet.api.service.tutor;

import br.com.alura.adopet.api.dto.tutor.atualizar.AtualizarTutorDTO;
import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.tutor.validacoes.atualizar.ValidacaoAtuaizacaoTutor;
import br.com.alura.adopet.api.service.tutor.validacoes.register.ValidacaoRegistroTutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private List<ValidacaoRegistroTutor> validacaoRegistroTutorList;

    @Mock
    private List<ValidacaoAtuaizacaoTutor> validacaoAtuaizacaoTutorList;

    @InjectMocks
    private TutorService tutorService;

    private TutorRegistroDTO tutorRegistroDTO;
    private AtualizarTutorDTO atualizarTutorDTO;
    private Tutor tutor;

    @BeforeEach
    void setUp() {
        // Inicializa o DTO com dados de exemplo
        tutorRegistroDTO = new TutorRegistroDTO("João", "(11)98765-4321", "email@exemplo.com");
        atualizarTutorDTO = new AtualizarTutorDTO("João", "(11)98765-4328");
        tutor = new Tutor(tutorRegistroDTO);
    }

    @Test
    void testRegistro() {
        // Simula as validações e o comportamento do repositório
        doNothing().when(validacaoRegistroTutorList).forEach(any());
        when(tutorRepository.save(any(Tutor.class))).thenReturn(tutor);

        // Chama o método de registro
        tutorService.registro(tutorRegistroDTO);

        // Verifica se as validações foram chamadas
        verify(validacaoRegistroTutorList).forEach(any());
        // Verifica se o repositório foi chamado para salvar o tutor
        verify(tutorRepository).save(any(Tutor.class));
    }

    @Test
    void testAtualizar() {
        // Simula as validações
        doNothing().when(validacaoAtuaizacaoTutorList).forEach(any());
        when(tutorRepository.getReferenceById(anyLong())).thenReturn(tutor);

        // Chama o método de atualização
        tutorService.atualizar(1L, atualizarTutorDTO);

        // Verifica se o método 'atualiza' foi chamado no objeto Tutor
        assertEquals(tutor.getTelefone(),atualizarTutorDTO.telefone());

    }
}
