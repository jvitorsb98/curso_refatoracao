package br.com.alura.adopet.api.dto.abrigo.details;

import br.com.alura.adopet.api.model.Abrigo;

/**
 * Data Transfer Object (DTO) para exibir os detalhes de um abrigo.
 * Este DTO é utilizado para transportar os dados de um abrigo para a camada de apresentação.
 */
public record AbrigoDetailsDTO(

        /**
         * Nome do abrigo.
         */
        String nome,

        /**
         * Email de contato do abrigo.
         */
        String email,

        /**
         * Número de telefone de contato do abrigo.
         */
        String telefone

) {

    /**
     * Construtor que inicializa o DTO a partir de uma entidade Abrigo.
     *
     * @param abrigo A entidade Abrigo que será convertida para o DTO.
     */
    public AbrigoDetailsDTO(Abrigo abrigo){
        this(abrigo.getNome(), abrigo.getEmail(), abrigo.getTelefone());
    }
}
