package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AprovacaoAdocaoDTO(

        @NotNull
        @Positive
        Long idAdocao
) {
}
