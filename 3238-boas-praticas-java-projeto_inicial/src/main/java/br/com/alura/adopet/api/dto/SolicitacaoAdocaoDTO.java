package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SolicitacaoAdocaoDTO(

        @NotNull
        @Positive
        Long idPet,


        @NotNull
        @Positive
        Long idTutor,


        @NotBlank
        String motivo

) {
}
