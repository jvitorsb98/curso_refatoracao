package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

    boolean existsByNome(String nome);

    boolean existsByTelefone(String telefone);

    boolean existsByEmail(String email);

    Abrigo findByNome(String nome);

    @Query("SELECT p FROM Pet p WHERE p.abrigo.id = :abrigoID")
    Page<Pet> findPetsByAbrigoWithId(@Param("abrigoID") Long abrigoID, Pageable pageable);

    @Query("SELECT p FROM Pet p WHERE p.abrigo.nome = :nomeAbrigo")
    Page<Pet> findPetsByAbrigoWithName(@Param("nomeAbrigo") String nomeAbrigo, Pageable pageable);

}
