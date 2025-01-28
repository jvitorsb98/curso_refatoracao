package br.com.alura.adopet.api.service.pet;

import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
        pageable = mock(Pageable.class);
    }

    @Test
    void testListarTodosDisponiveis() {
        // Criando uma lista de pets fictícios para o teste
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setNome("Rex");

        // Inicializando o abrigo do pet
        Abrigo abrigo1 = new Abrigo();
        abrigo1.setId(1L);  // Suponha que o Abrigo tenha um ID
        pet1.setAbrigo(abrigo1);

        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setNome("Boby");

        // Inicializando o abrigo do pet
        Abrigo abrigo2 = new Abrigo();
        abrigo2.setId(2L);  // Suponha que o Abrigo tenha um ID
        pet2.setAbrigo(abrigo2);

        // Criando uma Page com os pets fictícios
        List<Pet> pets = List.of(pet1, pet2);
        Page<Pet> petPage = new PageImpl<>(pets);

        // Configurando o mock do repositório para retornar a Page
        when(petRepository.findByDisponiveis(pageable)).thenReturn(petPage);

        // Chamando o método do serviço
        Page<PetDetailsDTO> result = petService.listarTodosDisponiveis(pageable);

        // Verificando se o resultado contém os dois pets
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());

        // Verificando se o método do repositório foi chamado corretamente
        verify(petRepository).findByDisponiveis(pageable);
    }

}
