package br.com.alura.adopet.api.dto.adocao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) utilizado para aprovar uma adoção.
 * Este DTO é usado para representar o ID de uma adoção que será aprovada.
 */
public record AprovacaoAdocaoDTO(

        /**
         * ID da adoção a ser aprovada.
         * O ID não pode ser nulo e deve ser um número positivo.
         */
        @NotNull
        @Positive
        Long idAdocao
) {
}
