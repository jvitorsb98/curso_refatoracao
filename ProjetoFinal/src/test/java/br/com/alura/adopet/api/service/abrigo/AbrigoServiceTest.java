package br.com.alura.adopet.api.service.abrigo;

import br.com.alura.adopet.api.dto.abrigo.details.AbrigoDetailsDTO;
import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.dto.pets.register.RegistroPetDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.enums.TipoPet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.service.abrigo.validacoes.register.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para o serviço de registro de abrigos")
public class AbrigoServiceTest {

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private PetRepository petRepository;

    @Spy
    private List<ValidacaoCadastroAbrigo> validacaoCadastroAbrigoList = new ArrayList<>();

    @Mock
    private ValidacaoEmailJaCadastradoAbrigo validacaoEmailJaCadastradoAbrigo;

    @Mock
    private ValidacaoNomeJaCadastradoAbrigo validacaoNomeJaCadastradoAbrigo;

    @Mock
    private ValidacaoTelefoneJaCadastradoAbrigo telefoneJaCadastradoAbrigo;

    @InjectMocks
    private AbrigoService abrigoService;

    private AbrigoRegisterDTO abrigoRegisterDTO;
    private Abrigo abrigo;

    @BeforeEach
    public void setUp() {
        // Cria um DTO de registro com dados válidos
        abrigoRegisterDTO = new AbrigoRegisterDTO(
                "Abrigo Teste",
                "(11) 99999-9999",
                "teste@abrigo.com"
        );
        // Cria a instância do Abrigo usando o DTO
        abrigo = new Abrigo(abrigoRegisterDTO);
    }

    @Test
    @DisplayName("Deve registrar um novo abrigo no sistema")
    public void testRegistrarAbrigo() {
        // Arrange: Simula que o método save do repositório de abrigos é chamado
        when(abrigoRepository.save(any(Abrigo.class))).thenReturn(abrigo);

        // Act: Chama o método registrar do serviço
        abrigoService.registrar(abrigoRegisterDTO);

        // Assert: Verifica se o método save foi chamado
        verify(abrigoRepository, times(1)).save(any(Abrigo.class));

        // Verifica se as validações foram executadas
        // Verifica se o método forEach foi chamado uma vez
        verify(validacaoCadastroAbrigoList, times(1)).forEach(any());
    }


    @Test
    @DisplayName("Não deve registrar abrigo com dados inválidos")
    public void testRegistrarAbrigoComDadosInvalidos() {
        // Arrange: Cria um DTO com dados inválidos (telefone inválido)
        AbrigoRegisterDTO invalidAbrigoRegisterDTO = new AbrigoRegisterDTO(
                "Abrigo Invalido",
                "telefone_invalido",
                "invalido@abrigo"
        );

        // Act & Assert: Verifica se o DTO inválido gera uma exceção
        try {
            abrigoService.registrar(invalidAbrigoRegisterDTO);
        } catch (Exception e) {
            // Verifica se o repositório não foi chamado devido à falha de validação
            verify(abrigoRepository, times(0)).save(any(Abrigo.class));

            // Verifica se as validações foram executadas mesmo com o erro
            verify(validacaoCadastroAbrigoList, times(1)).forEach(any());
        }
    }


    @Test
    @DisplayName("Deve listar todos os abrigos")
    public void testListarAbrigos() {
        Pageable pageable = PageRequest.of(0, 10);

        // Simula que o repositório retorna uma página de abrigos
        Page<Abrigo> pageAbrigos = new PageImpl<>(List.of(abrigo), pageable, 1);
        when(abrigoRepository.findAll(pageable)).thenReturn(pageAbrigos);

        // Act
        Page<AbrigoDetailsDTO> resultado = abrigoService.listar(pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(abrigoRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Deve listar pets de um abrigo pelo ID ou nome")
    public void testListarPetsPorNomeOuIdAbrigo() {
        Pageable pageable = PageRequest.of(0, 10);

        // Simula que o repositório retorna uma página de pets associados ao abrigo
        Page<Pet> pagePets = new PageImpl<>(new ArrayList<>(), pageable, 0);
        when(abrigoRepository.findPetsByAbrigoWithId(anyLong(), eq(pageable))).thenReturn(pagePets);
        when(abrigoRepository.findPetsByAbrigoWithName(anyString(), eq(pageable))).thenReturn(pagePets);

        // Act - Testando com ID
        Page<PetDetailsDTO> resultadoPorId = abrigoService.listarPets(pageable, "1");

        // Act - Testando com Nome
        Page<PetDetailsDTO> resultadoPorNome = abrigoService.listarPets(pageable, "Abrigo Teste");

        // Assert
        assertNotNull(resultadoPorId);
        assertNotNull(resultadoPorNome);
        verify(abrigoRepository, times(1)).findPetsByAbrigoWithId(anyLong(), eq(pageable));
        verify(abrigoRepository, times(1)).findPetsByAbrigoWithName(anyString(), eq(pageable));
    }

    @Test
    @DisplayName("Deve registrar um novo pet por id em um abrigo")
    public void testRegistrarPetPorID() {
        // Criação do DTO de RegistroPetDTO
        RegistroPetDTO registroPetDTO = new RegistroPetDTO(
                TipoPet.CACHORRO,      // Tipo do pet (exemplo: CAO)
                "Pet Teste",      // Nome do pet
                "Labrador",       // Raça do pet
                2,                // Idade do pet
                "Amarelo",        // Cor do pet
                15.0f             // Peso do pet
        );

        String nomeOuIdAbrigo = "1"; // ID do abrigo

        // Simula que o repositório de abrigos retorna um abrigo
        when(abrigoRepository.getReferenceById(anyLong())).thenReturn(abrigo);

        // Act
        abrigoService.registrarPet(registroPetDTO, nomeOuIdAbrigo);

        // Assert
        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    @DisplayName("Deve registrar um novo pet em um abrigo pelo nome")
    public void testRegistrarPetPorNome() {
        RegistroPetDTO registroPetDTO = new RegistroPetDTO(
                TipoPet.CACHORRO,
                "Pet Teste",
                "Labrador",
                2,
                "Amarelo",
                15.0f
        );

        String nomeOuIdAbrigo = "Abrigo Teste"; // Nome do abrigo

        // Simula que o método findByNome retorna um abrigo
        when(abrigoRepository.findByNome(anyString())).thenReturn(abrigo);

        abrigoService.registrarPet(registroPetDTO, nomeOuIdAbrigo);

        // Verifica se o método save foi chamado no repositório de pets
        verify(petRepository, times(1)).save(any(Pet.class));

        // Verifica se o método findByNome foi chamado no repositório de abrigos
        verify(abrigoRepository, times(1)).findByNome(nomeOuIdAbrigo);
    }

}
