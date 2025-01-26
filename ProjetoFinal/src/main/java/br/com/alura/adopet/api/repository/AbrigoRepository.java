package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável por fornecer operações de acesso a dados para a entidade {@link Abrigo}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, fornecendo métodos para manipulação de dados
 * da entidade {@link Abrigo}, como verificar a existência de um abrigo por nome, telefone ou e-mail,
 * bem como buscar pets associados a um abrigo específico.</p>
 */
@Repository
public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

    /**
     * Verifica se já existe um abrigo com o nome fornecido.
     *
     * @param nome O nome do abrigo a ser verificado.
     * @return true se o abrigo com o nome fornecido já existir, caso contrário, false.
     */
    boolean existsByNome(String nome);

    /**
     * Verifica se já existe um abrigo com o telefone fornecido.
     *
     * @param telefone O telefone do abrigo a ser verificado.
     * @return true se o abrigo com o telefone fornecido já existir, caso contrário, false.
     */
    boolean existsByTelefone(String telefone);

    /**
     * Verifica se já existe um abrigo com o e-mail fornecido.
     *
     * @param email O e-mail do abrigo a ser verificado.
     * @return true se o abrigo com o e-mail fornecido já existir, caso contrário, false.
     */
    boolean existsByEmail(String email);

    /**
     * Encontra um abrigo com base no nome fornecido.
     *
     * @param nome O nome do abrigo a ser encontrado.
     * @return O abrigo correspondente ao nome fornecido.
     */
    Abrigo findByNome(String nome);

    /**
     * Encontra todos os pets de um abrigo com base no ID do abrigo, retornando os resultados em forma de página.
     *
     * @param abrigoID O ID do abrigo.
     * @param pageable O objeto Pageable que permite a paginação dos resultados.
     * @return Uma página de pets associados ao abrigo com o ID fornecido.
     */
    @Query("SELECT p FROM Pet p WHERE p.abrigo.id = :abrigoID")
    Page<Pet> findPetsByAbrigoWithId(@Param("abrigoID") Long abrigoID, Pageable pageable);

    /**
     * Encontra todos os pets de um abrigo com base no nome do abrigo, retornando os resultados em forma de página.
     *
     * @param nomeAbrigo O nome do abrigo.
     * @param pageable O objeto Pageable que permite a paginação dos resultados.
     * @return Uma página de pets associados ao abrigo com o nome fornecido.
     */
    @Query("SELECT p FROM Pet p WHERE p.abrigo.nome = :nomeAbrigo")
    Page<Pet> findPetsByAbrigoWithName(@Param("nomeAbrigo") String nomeAbrigo, Pageable pageable);

}
