package br.com.alura.adopet.api.dto.pets.register;

import br.com.alura.adopet.api.model.TipoPet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistroPetDTO(

        @Enumerated(EnumType.STRING)
        TipoPet tipo,

        @NotBlank
        String nome,

        @NotBlank
        String raca,

        @NotNull
        @Positive
        Integer idade,

        @NotBlank
        String cor,

        @NotNull
        @Positive
        Float peso

) {
}
