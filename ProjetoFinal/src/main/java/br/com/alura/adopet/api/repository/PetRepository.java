package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável por fornecer operações de acesso a dados para a entidade {@link Pet}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, fornecendo métodos para manipulação de dados
 * da entidade {@link Pet}, como a busca de pets disponíveis para adoção.</p>
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    /**
     * Encontra todos os pets disponíveis para adoção (não adotados), retornando os resultados em forma de página.
     *
     * @param pageable O objeto Pageable que permite a paginação dos resultados.
     * @return Uma página de pets que estão disponíveis para adoção.
     */
    @Query("SELECT p FROM Pet p WHERE p.adotado = false")
    Page<Pet> findByDisponiveis(Pageable pageable);
}
