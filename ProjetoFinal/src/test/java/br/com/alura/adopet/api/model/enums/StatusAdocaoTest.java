package br.com.alura.adopet.api.model.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Teste da Enumeração StatusAdocao")
class StatusAdocaoTest {

    @Test
    @DisplayName("Deve validar que todos os valores da enumeração existem e estão corretos")
    void deveValidarValoresEnum() {
        // Arrange
        StatusAdocao[] expectedValues = {
                StatusAdocao.AGUARDANDO_AVALIACAO,
                StatusAdocao.APROVADO,
                StatusAdocao.REPROVADO
        };

        // Act
        StatusAdocao[] actualValues = StatusAdocao.values();

        // Assert
        assertNotNull(actualValues, "Os valores da enumeração não deveriam ser nulos.");
        assertEquals(expectedValues.length, actualValues.length, "O número de valores da enumeração está incorreto.");

        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], actualValues[i], "Os valores da enumeração não correspondem.");
        }
    }

    @Test
    @DisplayName("Deve validar que o valor AGUARDANDO_AVALIACAO existe e está correto")
    void deveValidarAguardandoAvaliacao() {
        // Arrange
        StatusAdocao statusEsperado = StatusAdocao.AGUARDANDO_AVALIACAO;

        // Act
        StatusAdocao statusAtual = StatusAdocao.valueOf("AGUARDANDO_AVALIACAO");

        // Assert
        assertNotNull(statusAtual, "O status AGUARDANDO_AVALIACAO não deveria ser nulo.");
        assertEquals(statusEsperado, statusAtual, "O status AGUARDANDO_AVALIACAO não é igual ao esperado.");
    }

    @Test
    @DisplayName("Deve validar que o valor APROVADO existe e está correto")
    void deveValidarAprovado() {
        // Arrange
        StatusAdocao statusEsperado = StatusAdocao.APROVADO;

        // Act
        StatusAdocao statusAtual = StatusAdocao.valueOf("APROVADO");

        // Assert
        assertNotNull(statusAtual, "O status APROVADO não deveria ser nulo.");
        assertEquals(statusEsperado, statusAtual, "O status APROVADO não é igual ao esperado.");
    }

    @Test
    @DisplayName("Deve validar que o valor REPROVADO existe e está correto")
    void deveValidarReprovado() {
        // Arrange
        StatusAdocao statusEsperado = StatusAdocao.REPROVADO;

        // Act
        StatusAdocao statusAtual = StatusAdocao.valueOf("REPROVADO");

        // Assert
        assertNotNull(statusAtual, "O status REPROVADO não deveria ser nulo.");
        assertEquals(statusEsperado, statusAtual, "O status REPROVADO não é igual ao esperado.");
    }
}
