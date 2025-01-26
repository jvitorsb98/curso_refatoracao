package br.com.alura.adopet.api.dto.pets.register;

import br.com.alura.adopet.api.model.enums.TipoPet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) utilizado para registrar um novo pet no sistema.
 * Este DTO contém os dados necessários para a criação de um pet, incluindo o tipo, nome, raça, idade,
 * cor e peso.
 */
public record RegistroPetDTO(

        /**
         * Tipo do pet (ex: cão, gato, etc.).
         * O tipo é um valor enumerado e deve ser informado.
         */
        @Enumerated(EnumType.STRING)
        TipoPet tipo,

        /**
         * Nome do pet.
         * Não pode ser nulo ou em branco.
         */
        @NotBlank
        String nome,

        /**
         * Raça do pet.
         * Não pode ser nula ou em branco.
         */
        @NotBlank
        String raca,

        /**
         * Idade do pet em anos.
         * Não pode ser nula e deve ser um valor positivo.
         */
        @NotNull
        @Positive
        Integer idade,

        /**
         * Cor do pet.
         * Não pode ser nula ou em branco.
         */
        @NotBlank
        String cor,

        /**
         * Peso do pet em quilos.
         * Não pode ser nulo e deve ser um valor positivo.
         */
        @NotNull
        @Positive
        Float peso

) {
}
