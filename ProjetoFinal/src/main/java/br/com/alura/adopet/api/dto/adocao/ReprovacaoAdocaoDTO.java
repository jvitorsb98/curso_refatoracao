package br.com.alura.adopet.api.dto.adocao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) utilizado para reprovar uma adoção.
 * Este DTO é usado para representar o ID de uma adoção que será reprovada
 * junto com a justificativa para a reprovação.
 */
public record ReprovacaoAdocaoDTO(

        /**
         * ID da adoção a ser reprovada.
         * O ID não pode ser nulo e deve ser um número positivo.
         */
        @NotNull
        @Positive
        Long idAdocao,

        /**
         * Justificativa para a reprovação da adoção.
         * A justificativa não pode ser em branco.
         */
        @NotBlank
        String justificativa

) {
}
