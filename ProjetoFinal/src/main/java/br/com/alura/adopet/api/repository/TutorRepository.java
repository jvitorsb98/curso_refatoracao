package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável por fornecer operações de acesso a dados para a entidade {@link Tutor}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, permitindo operações CRUD para manipulação de dados da entidade {@link Tutor}.</p>
 */
@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    /**
     * Verifica se já existe um tutor registrado com o número de telefone fornecido.
     *
     * @param telefone O número de telefone a ser verificado.
     * @return {@code true} se existir um tutor com o telefone fornecido, {@code false} caso contrário.
     */
    boolean existsByTelefone(String telefone);

    /**
     * Verifica se já existe um tutor registrado com o e-mail fornecido.
     *
     * @param email O e-mail a ser verificado.
     * @return {@code true} se existir um tutor com o e-mail fornecido, {@code false} caso contrário.
     */
    boolean existsByEmail(String email);
}
