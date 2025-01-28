package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste da Entidade Abrigo")
class AbrigoTest {

    @Test
    @DisplayName("Deve criar um Abrigo com base no DTO de registro")
    void deveCriarAbrigoComBaseNoDto() {
        // Arrange
        String nome = "Abrigo dos Animais";
        String telefone = "123456789";
        String email = "contato@abrigodosanimais.com";
        AbrigoRegisterDTO dto = new AbrigoRegisterDTO(nome, telefone, email);

        // Act
        Abrigo abrigo = new Abrigo(dto);

        // Assert
        assertNotNull(abrigo, "O abrigo não deveria ser nulo.");
        assertEquals(nome, abrigo.getNome(), "O nome do abrigo está incorreto.");
        assertEquals(telefone, abrigo.getTelefone(), "O telefone do abrigo está incorreto.");
        assertEquals(email, abrigo.getEmail(), "O email do abrigo está incorreto.");
    }

    @Test
    @DisplayName("Deve inicializar um Abrigo sem pets")
    void deveInicializarAbrigoSemPets() {
        // Arrange & Act
        Abrigo abrigo = new Abrigo();
        abrigo.setNome("Abrigo Esperança");
        abrigo.setTelefone("987654321");
        abrigo.setEmail("esperanca@abrigo.com");

        // Assert
        assertNotNull(abrigo, "O abrigo não deveria ser nulo.");
        assertNull(abrigo.getPets(), "A lista de pets deveria ser inicializada como nula ou vazia.");
        assertEquals("Abrigo Esperança", abrigo.getNome(), "O nome do abrigo está incorreto.");
        assertEquals("987654321", abrigo.getTelefone(), "O telefone do abrigo está incorreto.");
        assertEquals("esperanca@abrigo.com", abrigo.getEmail(), "O email do abrigo está incorreto.");
    }

    @Test
    @DisplayName("Deve permitir a associação de uma lista de pets ao abrigo")
    void deveAssociarListaDePets() {
        // Arrange
        Abrigo abrigo = new Abrigo();
        Pet pet1 = new Pet();
        pet1.setNome("Rex");
        Pet pet2 = new Pet();
        pet2.setNome("Mia");

        // Act
        abrigo.setPets(List.of(pet1, pet2));

        // Assert
        assertNotNull(abrigo.getPets(), "A lista de pets não deveria ser nula.");
        assertEquals(2, abrigo.getPets().size(), "A lista de pets deveria conter dois itens.");
        assertEquals("Rex", abrigo.getPets().get(0).getNome(), "O nome do primeiro pet está incorreto.");
        assertEquals("Mia", abrigo.getPets().get(1).getNome(), "O nome do segundo pet está incorreto.");
    }
}
