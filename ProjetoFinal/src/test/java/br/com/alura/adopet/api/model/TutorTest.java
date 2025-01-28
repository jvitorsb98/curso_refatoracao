package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.tutor.atualizar.AtualizarTutorDTO;
import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a entidade {@link Tutor}.
 */
class TutorTest {

    private Tutor tutor;

    @BeforeEach
    void setUp() {
        // Criação de um tutor para testes
        tutor = new Tutor();
        tutor.setId(1L);
        tutor.setNome("João Silva");
        tutor.setTelefone("(11) 98765-4321");
        tutor.setEmail("joao.silva@example.com");
        tutor.setAdocoes(new ArrayList<>());
    }

    @Test
    void deveCriarTutorComRegistroDTO() {
        // Dado um DTO de registro
        TutorRegistroDTO registroDTO = new TutorRegistroDTO("Maria Oliveira", "(21) 91234-5678", "maria.oliveira@example.com");

        // Quando um tutor é criado a partir do DTO
        Tutor novoTutor = new Tutor(registroDTO);

        // Então os dados do tutor devem corresponder ao DTO
        assertEquals("Maria Oliveira", novoTutor.getNome());
        assertEquals("(21) 91234-5678", novoTutor.getTelefone());
        assertEquals("maria.oliveira@example.com", novoTutor.getEmail());
    }

    @Test
    void deveAtualizarNomeETelefoneComAtualizarDTO() {
        // Dado um DTO de atualização
        AtualizarTutorDTO atualizarDTO = new AtualizarTutorDTO("Carlos Almeida", "(31) 92345-6789");

        // Quando o método de atualização é chamado
        tutor.atualiza(atualizarDTO);

        // Então o nome e telefone devem ser atualizados
        assertEquals("Carlos Almeida", tutor.getNome());
        assertEquals("(31) 92345-6789", tutor.getTelefone());

        // E o email deve permanecer inalterado
        assertEquals("joao.silva@example.com", tutor.getEmail());
    }

    @Test
    void naoDeveAtualizarComValoresNulosOuVazios() {
        // Dado um DTO de atualização com valores nulos e vazios
        AtualizarTutorDTO atualizarDTO = new AtualizarTutorDTO(null, "");

        // Quando o método de atualização é chamado
        tutor.atualiza(atualizarDTO);

        // Então os valores do tutor devem permanecer inalterados
        assertEquals("João Silva", tutor.getNome());
        assertEquals("(11) 98765-4321", tutor.getTelefone());
        assertEquals("joao.silva@example.com", tutor.getEmail());
    }

    @Test
    void deveAdicionarAdocoesNaLista() {
        // Dado uma lista de adoções
        List<Adocao> adocoes = new ArrayList<>();
        Adocao adocao1 = new Adocao();
        Adocao adocao2 = new Adocao();
        adocoes.add(adocao1);
        adocoes.add(adocao2);

        // Quando a lista de adoções é atribuída ao tutor
        tutor.setAdocoes(adocoes);

        // Então o tutor deve conter as adoções atribuídas
        assertEquals(2, tutor.getAdocoes().size());
        assertTrue(tutor.getAdocoes().contains(adocao1));
        assertTrue(tutor.getAdocoes().contains(adocao2));
    }

    @Test
    void gettersESettersDevemFuncionarCorretamente() {
        // Verifica os getters
        assertEquals(1L, tutor.getId());
        assertEquals("João Silva", tutor.getNome());
        assertEquals("(11) 98765-4321", tutor.getTelefone());
        assertEquals("joao.silva@example.com", tutor.getEmail());
        assertNotNull(tutor.getAdocoes());

        // Modifica os valores com setters
        tutor.setNome("Ana Costa");
        tutor.setTelefone("(41) 91234-5678");
        tutor.setEmail("ana.costa@example.com");

        // Verifica os valores após as modificações
        assertEquals("Ana Costa", tutor.getNome());
        assertEquals("(41) 91234-5678", tutor.getTelefone());
        assertEquals("ana.costa@example.com", tutor.getEmail());
    }
}
