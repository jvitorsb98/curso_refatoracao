package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.model.enums.TipoPet;
import br.com.alura.adopet.api.service.pet.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @InjectMocks
    private PetController petController;

    @Mock
    private PetService petService;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        pageable = Pageable.unpaged();
    }

    @Test
    @DisplayName("Listar pets disponíveis com sucesso")
    void testListarTodosDisponiveis() {
        // Cria uma lista de pets simulada
        Page<PetDetailsDTO> petDetailsDTOPage = new PageImpl<>(List.of(new PetDetailsDTO(TipoPet.CACHORRO, "Nome do Pet", "Poodle", 3, "Branco", new BigDecimal(5.5), false, 1L, null)));

        // Configura o comportamento do serviço
        when(petService.listarTodosDisponiveis(pageable)).thenReturn(petDetailsDTOPage);

        // Chama o método de listagem
        ResponseEntity<Page<PetDetailsDTO>> response = petController.listarTodosDisponiveis(pageable);

        // Verifica se a resposta está OK e se contém os dados esperados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
        verify(petService, times(1)).listarTodosDisponiveis(pageable);
    }
}
