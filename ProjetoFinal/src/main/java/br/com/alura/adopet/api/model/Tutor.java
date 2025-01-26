package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.tutor.atualizar.AtualizarTutorDTO;
import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Representa um tutor no sistema Adopet.
 *
 * <p>A classe Tutor é usada para armazenar informações sobre um tutor, incluindo seu nome, telefone,
 * e-mail, bem como suas adoções associadas.</p>
 */
@Entity
@Table(name = "tutores")
@Data
@NoArgsConstructor
public class Tutor {

    /**
     * Identificador único do tutor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do tutor.
     */
    private String nome;

    /**
     * Número de telefone do tutor.
     */
    private String telefone;

    /**
     * Endereço de e-mail do tutor.
     */
    private String email;

    /**
     * Lista de adoções realizadas pelo tutor.
     */
    @OneToMany(mappedBy = "tutor")
    private List<Adocao> adocoes;

    /**
     * Construtor utilizado para inicializar um tutor a partir de um DTO de registro.
     *
     * @param tutorRegistroDTO Objeto DTO contendo as informações para registro do tutor.
     */
    public Tutor(TutorRegistroDTO tutorRegistroDTO) {
        this.nome = tutorRegistroDTO.nome();
        this.telefone = tutorRegistroDTO.telefone();
        this.email = tutorRegistroDTO.email();
    }

    /**
     * Atualiza as informações do tutor com base em um DTO de atualização.
     *
     * <p>Este método verifica se os valores no DTO de atualização não são nulos ou vazios
     * antes de aplicar as mudanças no tutor.</p>
     *
     * @param atualizarTutorDTO Objeto DTO contendo as informações para atualizar o tutor.
     */
    public void atualiza(AtualizarTutorDTO atualizarTutorDTO) {
        if(atualizarTutorDTO.nome() != null && !atualizarTutorDTO.nome().isEmpty()){
            this.nome = atualizarTutorDTO.nome();
        }

        if(atualizarTutorDTO.telefone() != null && !atualizarTutorDTO.telefone().isEmpty()){
            this.telefone = atualizarTutorDTO.telefone();
        }
    }
}
