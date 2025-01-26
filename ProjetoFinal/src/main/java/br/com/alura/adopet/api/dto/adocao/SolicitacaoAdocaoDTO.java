package br.com.alura.adopet.api.dto.adocao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) utilizado para solicitar uma adoção de um pet.
 * Este DTO contém as informações necessárias para realizar a solicitação de adoção,
 * incluindo o ID do pet, o ID do tutor e o motivo da solicitação.
 */
public record SolicitacaoAdocaoDTO(

        /**
         * ID do pet que está sendo solicitado para adoção.
         * O ID não pode ser nulo e deve ser um número positivo.
         */
        @NotNull
        @Positive
        Long idPet,

        /**
         * ID do tutor que está solicitando a adoção.
         * O ID não pode ser nulo e deve ser um número positivo.
         */
        @NotNull
        @Positive
        Long idTutor,

        /**
         * Motivo da solicitação de adoção.
         * O motivo não pode ser em branco.
         */
        @NotBlank
        String motivo

) {
}
