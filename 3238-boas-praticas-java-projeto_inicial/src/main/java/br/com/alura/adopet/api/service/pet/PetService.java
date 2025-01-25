package br.com.alura.adopet.api.service.pet;

import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Page<PetDetailsDTO> listarTodosDisponiveis(Pageable pageable){
        return petRepository.findByDisponiveis(pageable).map(PetDetailsDTO::new);
    }



}
