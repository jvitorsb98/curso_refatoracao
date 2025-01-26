package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.enums.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável por fornecer operações de acesso a dados para a entidade {@link Adocao}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, permitindo operações CRUD e consultas personalizadas
 * para manipulação de dados da entidade {@link Adocao}, incluindo verificações sobre o status de adoção de um pet.</p>
 */
@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    /**
     * Verifica se existe uma adoção registrada para um pet específico e com um status específico.
     *
     * @param idPet O ID do pet a ser verificado.
     * @param statusAdocao O status de adoção a ser verificado.
     * @return {@code true} se existir uma adoção com o pet e o status fornecidos, {@code false} caso contrário.
     */
    boolean existsByPetIdAndStatus(Long idPet, StatusAdocao statusAdocao);

    boolean existsByTutorIdAndStatus(Long aLong, StatusAdocao statusAdocao);

    long countByTutorIdAndStatus(Long aLong, StatusAdocao statusAdocao);
}
