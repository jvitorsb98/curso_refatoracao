package br.com.alura.adopet.api.dto.abrigo.register;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Data Transfer Object (DTO) para registrar um novo abrigo.
 * Este DTO é utilizado para capturar os dados necessários para a criação de um abrigo.
 */
public record AbrigoRegisterDTO(

        /**
         * Nome do abrigo.
         * O nome não pode ser vazio.
         */
        @NotBlank
        String nome,

        /**
         * Número de telefone do abrigo.
         * O telefone deve ser um número válido no formato (XX) XXXXX-XXXX.
         */
        @NotBlank
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String telefone,

        /**
         * Email de contato do abrigo.
         * O email deve ser válido.
         */
        @NotBlank
        @Email
        String email

) {

}
