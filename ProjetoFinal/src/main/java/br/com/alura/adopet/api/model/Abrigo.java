package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Representa um abrigo no sistema Adopet.
 *
 * <p>Essa entidade é responsável por armazenar informações sobre abrigos, como nome, telefone,
 * email e a lista de pets associados.</p>
 *
 * <p>Utiliza anotações do JPA para mapeamento com o banco de dados, bem como
 * anotações do Lombok para geração automática de métodos.</p>
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "abrigos")
public class Abrigo {

    /**
     * Identificador único do abrigo.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do abrigo.
     */
    private String nome;

    /**
     * Telefone de contato do abrigo.
     */
    private String telefone;

    /**
     * Email de contato do abrigo.
     */
    private String email;

    /**
     * Lista de pets associados ao abrigo.
     *
     * <p>O relacionamento é unidirecional e utiliza cascade para permitir operações
     * em cascata, como persistência e remoção. O carregamento é feito com eager fetching
     * para garantir que os pets sejam carregados juntamente com o abrigo.</p>
     */
    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;

    /**
     * Construtor que inicializa o Abrigo com base em um DTO de registro.
     *
     * @param abrigoRegisterDTO Objeto DTO contendo os dados para registro do abrigo.
     */
    public Abrigo(AbrigoRegisterDTO abrigoRegisterDTO) {
        this.nome = abrigoRegisterDTO.nome();
        this.telefone = abrigoRegisterDTO.telefone();
        this.email = abrigoRegisterDTO.email();
    }
}
