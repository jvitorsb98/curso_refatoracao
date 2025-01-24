package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReprovacaoAdocaoDTO(

        @NotNull
        @Positive
        Long idAdocao,

        @NotBlank
        String justificativa

) {
}
