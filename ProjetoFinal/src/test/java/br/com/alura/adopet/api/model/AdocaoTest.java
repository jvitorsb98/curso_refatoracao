package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.model.enums.StatusAdocao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste da Entidade Adocao")
class AdocaoTest {

    @Test
    @DisplayName("Deve inicializar uma adoção com dados fornecidos no construtor")
    void deveInicializarAdocaoComDadosDoConstrutor() {
        // Arrange
        Tutor tutor = new Tutor();
        Pet pet = new Pet();
        String motivo = "Quero dar um lar amoroso para o pet";

        // Act
        Adocao adocao = new Adocao(tutor, pet, motivo);

        // Assert
        assertNotNull(adocao, "A adoção não deveria ser nula.");
        assertEquals(tutor, adocao.getTutor(), "O tutor não foi corretamente associado.");
        assertEquals(pet, adocao.getPet(), "O pet não foi corretamente associado.");
        assertEquals(motivo, adocao.getMotivo(), "O motivo da adoção está incorreto.");
        assertNotNull(adocao.getData(), "A data da adoção não deveria ser nula.");
        assertEquals(StatusAdocao.AGUARDANDO_AVALIACAO, adocao.getStatus(), "O status inicial deveria ser 'AGUARDANDO_AVALIACAO'.");
    }

    @Test
    @DisplayName("Deve reprovar uma adoção com justificativa")
    void deveReprovarAdocaoComJustificativa() {
        // Arrange
        Adocao adocao = new Adocao();
        String justificativa = "O tutor não atende aos requisitos mínimos.";

        // Act
        adocao.reprovar(justificativa);

        // Assert
        assertEquals(StatusAdocao.REPROVADO, adocao.getStatus(), "O status deveria ser 'REPROVADO'.");
        assertEquals(justificativa, adocao.getJustificativaStatus(), "A justificativa do status está incorreta.");
    }

    @Test
    @DisplayName("Deve aprovar uma adoção")
    void deveAprovarAdocao() {
        // Arrange
        Adocao adocao = new Adocao();

        // Act
        adocao.aprovar();

        // Assert
        assertEquals(StatusAdocao.APROVADO, adocao.getStatus(), "O status deveria ser 'APROVADO'.");
        assertNull(adocao.getJustificativaStatus(), "Não deveria haver justificativa ao aprovar a adoção.");
    }

    @Test
    @DisplayName("Deve inicializar uma adoção vazia")
    void deveInicializarAdocaoVazia() {
        // Arrange & Act
        Adocao adocao = new Adocao();

        // Assert
        assertNotNull(adocao, "A adoção não deveria ser nula.");
        assertNull(adocao.getTutor(), "O tutor deveria ser nulo inicialmente.");
        assertNull(adocao.getPet(), "O pet deveria ser nulo inicialmente.");
        assertNull(adocao.getMotivo(), "O motivo deveria ser nulo inicialmente.");
        assertNull(adocao.getData(), "A data deveria ser nula inicialmente.");
        assertNull(adocao.getStatus(), "O status deveria ser nulo inicialmente.");
        assertNull(adocao.getJustificativaStatus(), "A justificativa do status deveria ser nula inicialmente.");
    }
}
