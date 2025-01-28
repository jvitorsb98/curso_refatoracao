package br.com.alura.adopet.api.service.adocao;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.pets.register.RegistroPetDTO;
import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.model.enums.TipoPet;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.adocao.AdocaoService;
import br.com.alura.adopet.api.service.adocao.validacoes.aprovar.ValidacaoSolicitacaoAprovar;
import br.com.alura.adopet.api.service.adocao.validacoes.aprovar.ValidarAdocaoExisteParaAprovar;
import br.com.alura.adopet.api.service.adocao.validacoes.register.*;
import br.com.alura.adopet.api.service.adocao.validacoes.reprovar.ValidacaoSolicitacaoReprovar;
import br.com.alura.adopet.api.service.adocao.validacoes.reprovar.ValidarAdocaoExisteParaReprovar;
import br.com.alura.adopet.api.service.email.EmailService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService adocaoService;

    @Mock
    private EmailService emailService;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private ValidacaoPetComAdocaoEmAndamento validacaoPetComAdocaoEmAndamento;

    @Mock
    private ValidacaoPetDisponivel validacaoPetDisponivel;

    @Mock
    private ValidacaoTutorComAdocaoEmAndamento validacaoTutorComAdocaoEmAndamento;

    @Mock
    private ValidacaoTutorLimiteAdocoes validacaoTutorLimiteAdocoes;

    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacaoSolicitacaoAdocaoList = new ArrayList<>();

    @Spy
    private List<ValidacaoSolicitacaoAprovar> validacaoSolicitacaoAprovarList = new ArrayList<>();

    @Spy
    private List<ValidacaoSolicitacaoReprovar> validacaoSolicitacaoReprovarList = new ArrayList<>();

    @Mock
    private ValidarAdocaoExisteParaAprovar validarAdocaoExisteParaAprovar;

    @Mock
    private ValidarAdocaoExisteParaReprovar validarAdocaoExisteParaReprovar;
    private Adocao adocao;
    private Tutor tutor;
    private Pet pet;
    private Abrigo abrigo;

    private AbrigoRegisterDTO abrigoRegisterDTO;

    @BeforeEach
    void setUp() {
        RegistroPetDTO registroPetDTO = new RegistroPetDTO(
                TipoPet.CACHORRO,
                "Fido",
                "Labrador",
                7,
                "Preto",
                10.5f
        );


        // Inicializando o pet com o DTO e o abrigo
        pet = new Pet(registroPetDTO, abrigo);

        // Criando o DTO para o tutor
        TutorRegistroDTO tutorRegistroDTO = new TutorRegistroDTO(
                "João",
                "773451231",
                "email@email.com"
        );

        // Inicializando o tutor com o DTO
        tutor = new Tutor(tutorRegistroDTO);

        abrigoRegisterDTO = new AbrigoRegisterDTO(
                "Abrigo Teste",
                "(11) 99999-9999",
                "teste@abrigo.com"
        );
        // Cria a instância do Abrigo usando o DTO
        abrigo = new Abrigo(abrigoRegisterDTO);
        pet.setAbrigo(abrigo);
        adocao = new Adocao(tutor, pet, "Motivo para adoção");

        // Configura o comportamento esperado dos mocks
    }

    @Test
    @DisplayName("Deve validar a solicitação de adoção corretamente")
    void testSolicitarAdocao() {
        SolicitacaoAdocaoDTO dto = new SolicitacaoAdocaoDTO(1L, 1L, "Motivo válido");
        when(petRepository.getReferenceById(1L)).thenReturn(pet);
        when(tutorRepository.getReferenceById(1L)).thenReturn(tutor);
        when(adocaoRepository.save(ArgumentMatchers.any(Adocao.class))).thenReturn(adocao);

        assertDoesNotThrow(() -> adocaoService.solicitar(dto));

        // Verificando se o envio de e-mail foi chamado
        verify(emailService).send(anyString(), eq("Solicitação de adoção"), anyString());
    }


    @Test
    @DisplayName("Deve aprovar a adoção corretamente")
    void testAprovarAdocao() {
        AprovacaoAdocaoDTO dto = new AprovacaoAdocaoDTO(1L);
        when(adocaoRepository.getReferenceById(1L)).thenReturn(adocao);

        assertDoesNotThrow(() -> adocaoService.aprovar(dto));

        // Verificando se o envio de e-mail foi chamado
        verify(emailService).send(anyString(), eq("Adoção aprovada"), anyString());
    }

    @Test
    @DisplayName("Deve reprovar a adoção corretamente")
    void testReprovarAdocao() {
        ReprovacaoAdocaoDTO dto = new ReprovacaoAdocaoDTO(1L, "Motivo válido");
        when(adocaoRepository.getReferenceById(1L)).thenReturn(adocao);

        assertDoesNotThrow(() -> adocaoService.reprovar(dto));

        // Verificando se o envio de e-mail foi chamado
        verify(emailService).send(anyString(), eq("Adoção reprovada"), anyString());
    }

}
