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

/**
 * Serviço responsável pelo gerenciamento de abrigos e pets associados.
 *
 * <p>Essa classe oferece funcionalidades para registrar abrigos e pets, listar abrigos e pets, e realizar validações
 * durante o processo de cadastro de abrigos. Além disso, permite associar pets a um abrigo.</p>
 */
@Service
public class AbrigoService {

    @Autowired
    private List<ValidacaoCadastroAbrigo> validacaoCadastroAbrigoList;

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;

    /**
     * Registra um novo abrigo no sistema, realizando as validações antes do cadastro.
     *
     * <p>Valida os dados do abrigo antes de salvá-lo no banco de dados. Caso algum dado esteja incorreto, uma exceção
     * será lançada.</p>
     *
     * @param abrigoRegisterDTO O DTO contendo os dados do abrigo a ser registrado.
     */
    public void registrar(AbrigoRegisterDTO abrigoRegisterDTO) {
        validacaoCadastroAbrigoList.forEach(v -> v.validation(abrigoRegisterDTO));
        Abrigo abrigo = new Abrigo(abrigoRegisterDTO);
        abrigoRepository.save(abrigo);
    }

    /**
     * Lista os abrigos cadastrados no sistema com paginação.
     *
     * @param pageable A informação de paginação.
     * @return Uma página de objetos {@link AbrigoDetailsDTO} contendo os dados dos abrigos.
     */
    public Page<AbrigoDetailsDTO> listar(Pageable pageable) {
        return abrigoRepository.findAll(pageable).map(AbrigoDetailsDTO::new);
    }

    /**
     * Lista os pets associados a um abrigo específico, filtrando por nome ou ID do abrigo.
     *
     * <p>O filtro pode ser feito utilizando o nome ou o ID do abrigo. O resultado será uma lista de pets
     * pertencentes ao abrigo informado.</p>
     *
     * @param pageable A informação de paginação.
     * @param nomeOuIdAbrigo O nome ou ID do abrigo.
     * @return Uma página de objetos {@link PetDetailsDTO} contendo os dados dos pets associados ao abrigo.
     */
    public Page<PetDetailsDTO> listarPets(Pageable pageable, String nomeOuIdAbrigo) {
        if (nomeOuIdAbrigo.matches("\\d+")) {
            return abrigoRepository.findPetsByAbrigoWithId(Long.parseLong(nomeOuIdAbrigo), pageable).map(PetDetailsDTO::new);
        } else {
            return abrigoRepository.findPetsByAbrigoWithName(nomeOuIdAbrigo, pageable).map(PetDetailsDTO::new);
        }
    }

    /**
     * Registra um novo pet associado a um abrigo específico.
     *
     * <p>Associa o pet ao abrigo encontrado, seja pelo nome ou ID, e realiza o cadastro do pet no sistema.</p>
     *
     * @param registroPetDTO O DTO contendo os dados do pet a ser registrado.
     * @param nomeOuIdAbrigo O nome ou ID do abrigo ao qual o pet será associado.
     */
    public void registrarPet(RegistroPetDTO registroPetDTO, String nomeOuIdAbrigo) {
        Abrigo abrigo = null;
        if (nomeOuIdAbrigo.matches("\\d+")) {
            abrigo = abrigoRepository.getReferenceById(Long.parseLong(nomeOuIdAbrigo));
        } else {
            abrigo = abrigoRepository.findByNome(nomeOuIdAbrigo);
        }
        Pet pet = new Pet(registroPetDTO, abrigo);
        petRepository.save(pet);
    }
}
