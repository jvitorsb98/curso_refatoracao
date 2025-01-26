package br.com.alura.adopet.api.dto.pets.details;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.enums.TipoPet;
import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) utilizado para detalhar as informações de um pet.
 * Este DTO contém os dados de um pet específico, incluindo o tipo, nome, raça, idade,
 * cor, peso, status de adoção, e as IDs do abrigo e da adoção associada.
 */
public record PetDetailsDTO(

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
        String nome,

        /**
         * Raça do pet.
         * Não pode ser nula ou em branco.
         */
        String raca,

        /**
         * Idade do pet em anos.
         * Não pode ser nula.
         */
        Integer idade,

        /**
         * Cor do pet.
         * Não pode ser nula ou em branco.
         */
        String cor,

        /**
         * Peso do pet em quilos.
         * Não pode ser nulo.
         */
        BigDecimal peso,

        /**
         * Indica se o pet foi adotado ou não.
         * O valor pode ser verdadeiro (adotado) ou falso (não adotado).
         */
        Boolean adotado,

        /**
         * ID do abrigo onde o pet está localizado.
         * A ID não pode ser nula.
         */
        Long idAbrigo,

        /**
         * ID da adoção associada ao pet.
         * A ID pode ser nula se o pet não foi adotado.
         */
        Long idAdocao

) {

    /**
     * Construtor que cria um {@link PetDetailsDTO} a partir de um modelo {@link Pet}.
     * Este construtor mapeia os dados do modelo {@link Pet} para os campos do DTO.
     *
     * @param pet Objeto do tipo {@link Pet} que contém os dados do pet a ser transferido.
     */
    public PetDetailsDTO(Pet pet){
        this(pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(),
                pet.getPeso(), pet.getAdotado(), pet.getAbrigo().getId(), pet.getAdocao() != null ? pet.getAdocao().getId() : null);
    }
}
