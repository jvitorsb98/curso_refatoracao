package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.tutor.atualizar.AtualizarTutorDTO;
import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tutores")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;


    private String telefone;


    private String email;

    @OneToMany(mappedBy = "tutor")
    private List<Adocao> adocoes;

    public Tutor(TutorRegistroDTO tutorRegistroDTO) {
        this.nome = tutorRegistroDTO.nome();
        this.telefone = tutorRegistroDTO.telefone();
        this.email = tutorRegistroDTO.email();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(id, tutor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Adocao> getAdocoes() {
        return adocoes;
    }

    public void setAdocoes(List<Adocao> adocoes) {
        this.adocoes = adocoes;
    }

    public void atualiza(AtualizarTutorDTO atualizarTutorDTO) {
        if(atualizarTutorDTO.nome() != null && !atualizarTutorDTO.nome().isEmpty()){
            this.nome = atualizarTutorDTO.nome();
        }

        if(atualizarTutorDTO.telefone() != null && !atualizarTutorDTO.telefone().isEmpty()){
            this.telefone = atualizarTutorDTO.telefone();
        }
    }
}
