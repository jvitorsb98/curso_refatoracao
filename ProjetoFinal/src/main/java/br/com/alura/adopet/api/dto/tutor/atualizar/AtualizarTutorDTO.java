package br.com.alura.adopet.api.dto.tutor.atualizar;

/**
 * Data Transfer Object (DTO) utilizado para atualizar os dados de um tutor no sistema.
 * Este DTO contém os campos opcionais que podem ser alterados: nome e telefone.
 */
public record AtualizarTutorDTO(

        /**
         * Nome do tutor.
         * Este campo é opcional e pode ser fornecido para atualizar o nome do tutor.
         */
        String nome,

        /**
         * Telefone do tutor.
         * Este campo é opcional e pode ser fornecido para atualizar o número de telefone do tutor.
         */
        String telefone

) {
}
