package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.pets.register.RegistroPetDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPet tipo;

    private String nome;

    private String raca;

    private Integer idade;

    private String cor;

    private BigDecimal peso;

    private Boolean adotado;

    @ManyToOne
    private Abrigo abrigo;

    @OneToOne(mappedBy = "pet")
    private Adocao adocao;

    public Pet(RegistroPetDTO registroPetDTO, Abrigo abrigo) {
        this.tipo = registroPetDTO.tipo();
        this.raca = registroPetDTO.raca();
        this.nome = registroPetDTO.nome();
        this.cor = registroPetDTO.cor();
        this.peso = new BigDecimal(registroPetDTO.peso());
        this.adotado = false;
        this.abrigo = abrigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
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

    public TipoPet getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }


    public BigDecimal getPeso() {
        return peso;
    }

    public Boolean getAdotado() {
        return adotado;
    }


    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }

    public Adocao getAdocao() {
        return adocao;
    }

    public void setAdocao(Adocao adocao) {
        this.adocao = adocao;
    }
}
