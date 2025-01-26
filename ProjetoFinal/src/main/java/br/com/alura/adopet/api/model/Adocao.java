package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.model.enums.StatusAdocao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Representa uma adoção no sistema Adopet.
 *
 * <p>Essa entidade é responsável por armazenar informações sobre o processo de adoção,
 * incluindo tutor, pet, data da adoção, status e motivo.</p>
 *
 * <p>Utiliza anotações do JPA para mapeamento com o banco de dados e Lombok para simplificação do código.</p>
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "adocoes")
public class Adocao {

    /**
     * Identificador único da adoção.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Data em que a adoção foi registrada.
     * Inicializada automaticamente no momento da criação.
     */
    private LocalDateTime data;

    /**
     * Tutor associado à adoção.
     * Relacionamento muitos-para-um (ManyToOne) com carregamento lazy.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    /**
     * Pet associado à adoção.
     * Relacionamento um-para-um (OneToOne) com carregamento lazy.
     */
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    /**
     * Motivo apresentado para a adoção.
     */
    private String motivo;

    /**
     * Status da adoção.
     * Utiliza enumeração {@link StatusAdocao}.
     */
    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    /**
     * Justificativa para o status da adoção.
     * Utilizado em casos de reprovação.
     */
    private String justificativaStatus;

    /**
     * Construtor que inicializa uma adoção com base no tutor, pet e motivo.
     *
     * @param tutor Tutor responsável pela adoção.
     * @param pet Pet a ser adotado.
     * @param motivo Motivo para a adoção.
     */
    public Adocao(Tutor tutor, Pet pet, String motivo) {
        this.tutor = tutor;
        this.pet = pet;
        this.motivo = motivo;
        this.data = LocalDateTime.now();
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
    }

    /**
     * Reprova a adoção com uma justificativa específica.
     *
     * @param justificativa Motivo pelo qual a adoção foi reprovada.
     */
    public void reprovar(String justificativa) {
        this.status = StatusAdocao.REPROVADO;
        this.justificativaStatus = justificativa;
    }

    /**
     * Aprova a adoção.
     */
    public void aprovar() {
        this.status = StatusAdocao.APROVADO;
    }
}
