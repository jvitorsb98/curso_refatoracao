package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.pets.register.RegistroPetDTO;
import br.com.alura.adopet.api.model.enums.TipoPet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Representa um pet no sistema Adopet.
 *
 * <p>Essa entidade é responsável por armazenar informações sobre os pets,
 * incluindo tipo, nome, raça, idade, cor, peso, status de adoção e o abrigo ao qual pertence.</p>
 *
 * <p>Utiliza anotações do JPA para mapeamento com o banco de dados e Lombok para simplificação do código.</p>
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet {

    /**
     * Identificador único do pet.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo do pet (por exemplo, CÃO ou GATO).
     * Representado pela enum {@link TipoPet}.
     */
    @Enumerated(EnumType.STRING)
    private TipoPet tipo;

    /**
     * Nome do pet.
     */
    private String nome;

    /**
     * Raça do pet.
     */
    private String raca;

    /**
     * Idade do pet em anos.
     */
    private Integer idade;

    /**
     * Cor do pet.
     */
    private String cor;

    /**
     * Peso do pet em quilogramas.
     */
    private BigDecimal peso;

    /**
     * Indica se o pet já foi adotado.
     */
    private Boolean adotado;

    /**
     * Abrigo ao qual o pet pertence.
     * Relacionamento muitos-para-um (ManyToOne).
     */
    @ManyToOne
    private Abrigo abrigo;

    /**
     * Adoção associada ao pet.
     * Relacionamento um-para-um (OneToOne).
     */
    @OneToOne(mappedBy = "pet")
    private Adocao adocao;

    /**
     * Construtor que inicializa um pet com base em um DTO de registro e um abrigo.
     *
     * @param registroPetDTO Objeto DTO contendo as informações de registro do pet.
     * @param abrigo Abrigo ao qual o pet será associado.
     */
    public Pet(RegistroPetDTO registroPetDTO, Abrigo abrigo) {
        this.tipo = registroPetDTO.tipo();
        this.raca = registroPetDTO.raca();
        this.nome = registroPetDTO.nome();
        this.cor = registroPetDTO.cor();
        this.peso = new BigDecimal(registroPetDTO.peso());
        this.adotado = false; // Inicializa como não adotado
        this.abrigo = abrigo;
    }
}
