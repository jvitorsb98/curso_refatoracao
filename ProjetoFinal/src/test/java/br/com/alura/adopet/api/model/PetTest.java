package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.pets.register.RegistroPetDTO;
import br.com.alura.adopet.api.model.enums.TipoPet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para a entidade {@link Pet}.
 */
class PetTest {

    private RegistroPetDTO registroPetDTO;
    private Abrigo abrigo;

    /**
     * Configuração inicial antes de cada teste.
     */
    @BeforeEach
    void setUp() {
        registroPetDTO = new RegistroPetDTO(
                TipoPet.CACHORRO,
                "Rex",
                "Pastor Alemão",
                4,
                "Marrom",
                20.0f
        );

        abrigo = new Abrigo();
        abrigo.setId(1L);
        abrigo.setNome("Abrigo Feliz");
    }

    /**
     * Testa o construtor da entidade {@link Pet} com base no DTO de registro.
     */
    @Test
    void testPetConstructorWithDTO() {
        Pet pet = new Pet(registroPetDTO, abrigo);

        assertEquals(TipoPet.CACHORRO, pet.getTipo());
        assertEquals("Pastor Alemão", pet.getRaca());
        assertEquals("Rex", pet.getNome());
        assertEquals("Marrom", pet.getCor());
        assertEquals(new BigDecimal("20"), pet.getPeso());
        assertFalse(pet.getAdotado());
        assertEquals(abrigo, pet.getAbrigo());
    }

    /**
     * Testa os getters e setters da entidade {@link Pet}.
     */
    @Test
    void testGettersAndSetters() {
        Pet pet = new Pet();

        pet.setId(1L);
        pet.setTipo(TipoPet.GATO);
        pet.setNome("Mia");
        pet.setRaca("Siames");
        pet.setIdade(3);
        pet.setCor("Cinza");
        pet.setPeso(new BigDecimal("4.5"));
        pet.setAdotado(true);
        pet.setAbrigo(abrigo);

        assertEquals(1L, pet.getId());
        assertEquals(TipoPet.GATO, pet.getTipo());
        assertEquals("Mia", pet.getNome());
        assertEquals("Siames", pet.getRaca());
        assertEquals(3, pet.getIdade());
        assertEquals("Cinza", pet.getCor());
        assertEquals(new BigDecimal("4.5"), pet.getPeso());
        assertTrue(pet.getAdotado());
        assertEquals(abrigo, pet.getAbrigo());
    }
}
