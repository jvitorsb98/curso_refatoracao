package br.com.alura.adopet.api.dto.abrigo.details;

import br.com.alura.adopet.api.model.Abrigo;

public record AbrigoDetailsDTO(

        String nome,

        String email,

        String telefone

) {

    public AbrigoDetailsDTO(Abrigo abrigo){
        this(abrigo.getNome(), abrigo.getEmail(), abrigo.getTelefone());
    }
}
