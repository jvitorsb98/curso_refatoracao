package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.tutor.atualizar.AtualizarTutorDTO;
import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.service.tutor.TutorService;
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
class TutorControllerTest {

    @InjectMocks
    private TutorController tutorController;

    @Mock
    private TutorService tutorService;

    private TutorRegistroDTO tutorRegistroDTO;
    private AtualizarTutorDTO atualizarTutorDTO;

    @BeforeEach
    void setUp() {
        tutorRegistroDTO = new TutorRegistroDTO("Nome do Tutor", "email@dominio.com", "123456789");
        atualizarTutorDTO = new AtualizarTutorDTO("Novo Nome", "987654321");
    }

    @Test
    @DisplayName("Cadastrar tutor com sucesso")
    void testCadastrar() {
        // Configura o comportamento do serviço
        doNothing().when(tutorService).registro(tutorRegistroDTO);

        // Chama o método de cadastro
        ResponseEntity<String> response = tutorController.cadastrar(tutorRegistroDTO);

        // Verifica se a resposta está OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(tutorService, times(1)).registro(tutorRegistroDTO);
    }

    @Test
    @DisplayName("Atualizar tutor com sucesso")
    void testAtualizar() {
        // Configura o comportamento do serviço
        doNothing().when(tutorService).atualizar(1L, atualizarTutorDTO);

        // Chama o método de atualização
        ResponseEntity<String> response = tutorController.atualizar(1L, atualizarTutorDTO);

        // Verifica se a resposta está OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(tutorService, times(1)).atualizar(1L, atualizarTutorDTO);
    }
}
