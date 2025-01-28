package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.abrigo.details.AbrigoDetailsDTO;
import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.dto.pets.register.RegistroPetDTO;
import br.com.alura.adopet.api.model.enums.TipoPet;
import br.com.alura.adopet.api.service.abrigo.AbrigoService;
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
class AbrigoControllerTest {

    @InjectMocks
    private AbrigoController abrigoController;

    @Mock
    private AbrigoService abrigoService;

    private AbrigoRegisterDTO abrigoRegisterDTO;
    private RegistroPetDTO registroPetDTO;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        // Atualiza o AbrigoRegisterDTO com os campos corretos
        abrigoRegisterDTO = new AbrigoRegisterDTO("Nome do Abrigo", "(11) 91234-5678", "email@abrigo.com");

        // Atualiza o RegistroPetDTO com os campos corretos
        registroPetDTO = new RegistroPetDTO(TipoPet.CACHORRO, "Nome do Pet", "Poodle", 3, "Branco", 5.5f);

        pageable = Pageable.unpaged();
    }

    @Test
    @DisplayName("Cadastrar um novo abrigo com sucesso")
    void testCadastrar() {
        // Configura o comportamento do serviço
        doNothing().when(abrigoService).registrar(abrigoRegisterDTO);

        // Chama o método de cadastro
        ResponseEntity<String> response = abrigoController.cadastrar(abrigoRegisterDTO);

        // Verifica se a resposta está OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(abrigoService, times(1)).registrar(abrigoRegisterDTO);
    }

    @Test
    @DisplayName("Listar abrigos com sucesso")
    void testListar() {
        // Cria uma lista de abrigos simulada
        Page<AbrigoDetailsDTO> abrigoDetailsDTOPage = new PageImpl<>(List.of(new AbrigoDetailsDTO("Nome do Abrigo", "email@abrigo.com", "(11) 91234-5678")));

        // Configura o comportamento do serviço
        when(abrigoService.listar(pageable)).thenReturn(abrigoDetailsDTOPage);

        // Chama o método de listagem
        ResponseEntity<Page<AbrigoDetailsDTO>> response = abrigoController.listar(pageable);

        // Verifica se a resposta está OK e se contém os dados esperados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
        verify(abrigoService, times(1)).listar(pageable);
    }

    @Test
    @DisplayName("Listar pets de um abrigo com sucesso")
    void testListarPets() {
        // Cria uma lista de pets simulada
        Page<PetDetailsDTO> petDetailsDTOPage = new PageImpl<>(List.of(new PetDetailsDTO(TipoPet.CACHORRO, "Nome do Pet", "Poodle", 3, "Branco", new BigDecimal(5.5), false, 1L, null)));

        // Configura o comportamento do serviço
        when(abrigoService.listarPets(pageable, "AbrigoTest")).thenReturn(petDetailsDTOPage);

        // Chama o método de listagem de pets
        ResponseEntity<Page<PetDetailsDTO>> response = abrigoController.listarPets("AbrigoTest", pageable);

        // Verifica se a resposta está OK e se contém os dados esperados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
        verify(abrigoService, times(1)).listarPets(pageable, "AbrigoTest");
    }

    @Test
    @DisplayName("Cadastrar um pet em um abrigo com sucesso")
    void testCadastrarPet() {
        // Configura o comportamento do serviço
        doNothing().when(abrigoService).registrarPet(registroPetDTO, "AbrigoTest");

        // Chama o método de cadastro de pet
        ResponseEntity<String> response = abrigoController.cadastrarPet("AbrigoTest", registroPetDTO);

        // Verifica se a resposta está OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(abrigoService, times(1)).registrarPet(registroPetDTO, "AbrigoTest");
    }
}
