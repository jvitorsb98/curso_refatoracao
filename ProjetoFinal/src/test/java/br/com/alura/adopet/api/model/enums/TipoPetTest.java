package br.com.alura.adopet.api.model.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Teste da Enumeração TipoPet")
class TipoPetTest {

    @Test
    @DisplayName("Deve validar que todos os valores da enumeração existem e estão corretos")
    void deveValidarValoresEnum() {
        // Arrange
        TipoPet[] valoresEsperados = {
                TipoPet.GATO,
                TipoPet.CACHORRO
        };

        // Act
        TipoPet[] valoresAtuais = TipoPet.values();

        // Assert
        assertNotNull(valoresAtuais, "Os valores da enumeração não deveriam ser nulos.");
        assertEquals(valoresEsperados.length, valoresAtuais.length, "O número de valores da enumeração está incorreto.");

        for (int i = 0; i < valoresEsperados.length; i++) {
            assertEquals(valoresEsperados[i], valoresAtuais[i], "Os valores da enumeração não correspondem.");
        }
    }

    @Test
    @DisplayName("Deve validar que o valor GATO existe e está correto")
    void deveValidarGato() {
        // Arrange
        TipoPet tipoEsperado = TipoPet.GATO;

        // Act
        TipoPet tipoAtual = TipoPet.valueOf("GATO");

        // Assert
        assertNotNull(tipoAtual, "O tipo GATO não deveria ser nulo.");
        assertEquals(tipoEsperado, tipoAtual, "O tipo GATO não é igual ao esperado.");
    }

    @Test
    @DisplayName("Deve validar que o valor CACHORRO existe e está correto")
    void deveValidarCachorro() {
        // Arrange
        TipoPet tipoEsperado = TipoPet.CACHORRO;

        // Act
        TipoPet tipoAtual = TipoPet.valueOf("CACHORRO");

        // Assert
        assertNotNull(tipoAtual, "O tipo CACHORRO não deveria ser nulo.");
        assertEquals(tipoEsperado, tipoAtual, "O tipo CACHORRO não é igual ao esperado.");
    }
}
