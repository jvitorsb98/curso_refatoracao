package br.com.alura.adopet.api.service.abrigo;

import br.com.alura.adopet.api.dto.abrigo.details.AbrigoDetailsDTO;
import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.dto.pets.register.RegistroPetDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.service.abrigo.validacoes.register.ValidacaoCadastroAbrigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private List<ValidacaoCadastroAbrigo> validacaoCadastroAbrigoList;

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;

    public void registrar(AbrigoRegisterDTO abrigoRegisterDTO){
        validacaoCadastroAbrigoList.forEach(v -> v.validation(abrigoRegisterDTO));
        Abrigo abrigo = new Abrigo(abrigoRegisterDTO);
        abrigoRepository.save(abrigo);
    }

    public Page<AbrigoDetailsDTO> listar(Pageable pageable) {
        return abrigoRepository.findAll(pageable).map(AbrigoDetailsDTO::new);
    }

    public Page<PetDetailsDTO> listarPets(Pageable pageable, String nomeOuIdAbrigo){
        if(nomeOuIdAbrigo.matches("\\d+")){
            return abrigoRepository.findPetsByAbrigoWithId(Long.parseLong(nomeOuIdAbrigo),pageable).map(PetDetailsDTO::new);
        }else{
            return abrigoRepository.findPetsByAbrigoWithName(nomeOuIdAbrigo, pageable).map(PetDetailsDTO::new);
        }
    }

    public void registrarPet(RegistroPetDTO registroPetDTO, String nomeOuIdAbrigo){
        Abrigo abrigo = null;
        if(nomeOuIdAbrigo.matches("\\d+")){
            abrigo = abrigoRepository.getReferenceById(Long.parseLong(nomeOuIdAbrigo));
        }else{
            abrigo = abrigoRepository.findByNome(nomeOuIdAbrigo);
        }
        Pet pet = new Pet(registroPetDTO, abrigo);
        petRepository.save(pet);
    }


}
