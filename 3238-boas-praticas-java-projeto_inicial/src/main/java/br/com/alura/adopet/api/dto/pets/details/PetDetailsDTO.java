package br.com.alura.adopet.api.dto.pets.details;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PetDetailsDTO(

        @Enumerated(EnumType.STRING)
        TipoPet tipo,

        String nome,

        String raca,

        Integer idade,

        String cor,

        BigDecimal peso,

        Boolean adotado,

        Long idAbrigo,


        Long idAdocao

) {

    public PetDetailsDTO(Pet pet){
        this(pet.getTipo(),pet.getNome(),pet.getRaca(),pet.getIdade(),pet.getCor(),pet.getPeso()
                ,pet.getAdotado(),pet.getAbrigo().getId(),pet.getAdocao().getId());
    }
}
