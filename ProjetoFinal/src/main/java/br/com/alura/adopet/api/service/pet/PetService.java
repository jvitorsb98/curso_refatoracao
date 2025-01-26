package br.com.alura.adopet.api.service.pet;

import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pela gestão de {@link Pet}s.
 *
 * <p>Esta classe fornece métodos para listar os {@link Pet}s disponíveis para adoção,
 * utilizando o repositório {@link PetRepository} para acessar os dados.</p>
 */
@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    /**
     * Retorna uma página de {@link PetDetailsDTO}s com informações detalhadas dos pets disponíveis para adoção.
     *
     * <p>Este método consulta o repositório {@link PetRepository} para obter uma lista de pets que não foram adotados
     * e os retorna em formato de página, permitindo paginação dos resultados.</p>
     *
     * @param pageable Objeto que contém as informações de paginação.
     * @return Uma página de {@link PetDetailsDTO}s com os pets disponíveis para adoção.
     */
    public Page<PetDetailsDTO> listarTodosDisponiveis(Pageable pageable){
        return petRepository.findByDisponiveis(pageable).map(PetDetailsDTO::new);
    }
}
