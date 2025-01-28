package br.com.alura.adopet.api.dto.tutor.register;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Data Transfer Object (DTO) utilizado para registrar um novo tutor no sistema.
 * Este DTO contém os dados necessários para o registro: nome, telefone e e-mail.
 */
public record TutorRegistroDTO(

        /**
         * Nome do tutor.
         * Este campo é obrigatório e não pode estar em branco.
         */
        @NotBlank
        String nome,

        /**
         * Telefone do tutor.
         * Este campo é obrigatório e deve seguir o padrão de validação:
         * (##)#####-#### ou (##)####-####.
         */
        @NotBlank
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String telefone,

        /**
         * E-mail do tutor.
         * Este campo é obrigatório e deve ser um endereço de e-mail válido.
         */
        @NotBlank
        @Email
        String email

) {
}
