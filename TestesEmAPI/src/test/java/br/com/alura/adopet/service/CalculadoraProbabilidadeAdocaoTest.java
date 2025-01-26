package br.com.alura.adopet.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.service.CalculadoraProbabilidadeAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste da classe CalculadoraProbabilidadeAdocao")
public class CalculadoraProbabilidadeAdocaoTest {

    @Test
    public void deveriaRetornarProbabilidadeAltaParaPetComPesoBaixoEIdadeBaixa(){

        //ARRANGE
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "74999999999",
                "abrigofeliz@email.com.br"
        ));

        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                4,
                "Cinza",
                4.0f
        ), abrigo);
        CalculadoraProbabilidadeAdocao calculadoraProbabilidadeAdocao = new CalculadoraProbabilidadeAdocao();
        //ACT
        ProbabilidadeAdocao probabilidadeAdocao = calculadoraProbabilidadeAdocao.calcular(pet);
        //ASSERT
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA,probabilidadeAdocao);
    }

    @Test
    public void deveriaRetornarProbabilidadeMediaParaPetComPesoBaixoEIdadeAlta(){
        //ARRANGE
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "74999999999",
                "abrigofeliz@email.com.br"
        ));

        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                15,
                "Cinza",
                4.0f
        ), abrigo);

        CalculadoraProbabilidadeAdocao calculadoraProbabilidadeAdocao = new CalculadoraProbabilidadeAdocao();
        //ACT
        ProbabilidadeAdocao probabilidadeAdocao = calculadoraProbabilidadeAdocao.calcular(pet);
        //ASSERT
        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA,probabilidadeAdocao);
    }

}
