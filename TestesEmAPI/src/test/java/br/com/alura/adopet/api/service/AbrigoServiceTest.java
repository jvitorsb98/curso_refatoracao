package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {


    @InjectMocks
    private AdocaoService service;

    @Mock
    private AdocaoRepository repository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private EmailService emailService;


    @Mock
    private Pet pet;

    @Mock
    private Tutor tutor;

    @Mock
    private Abrigo abrigo;

    private SolicitacaoAdocaoDto dto;

    @Captor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacaoSolicitacaoAdocaoList = new ArrayList<>();

    @Mock
    private ValidacaoSolicitacaoAdocao validacaoSolicitacaoAdocao;

    @Mock
    private ValidacaoSolicitacaoAdocao validacaoSolicitacaoAdocao2;

    @Test
    void deveriaSalvarAdocaoNoBanco(){
        //ARRANGE
        dto = new SolicitacaoAdocaoDto(10l , 20l , "motivo qualquer");
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        //ACT
        service.solicitar(dto);

        //ASSERT
        then(repository).should().save(adocaoCaptor.capture());
        Adocao adocao = adocaoCaptor.getValue();
        Assertions.assertEquals(pet,adocao.getPet());
        Assertions.assertEquals(tutor,adocao.getTutor());
        Assertions.assertEquals(dto.motivo(),adocao.getMotivo());

    }

    @Test
    void deveriaChamarOsValidadores(){
        dto = new SolicitacaoAdocaoDto(10l , 20l , "motivo qualquer");
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        validacaoSolicitacaoAdocaoList.add(validacaoSolicitacaoAdocao);
        validacaoSolicitacaoAdocaoList.add(validacaoSolicitacaoAdocao2);
        //ACT
        service.solicitar(dto);

        //ASSERT
        then(validacaoSolicitacaoAdocao).should().validar(dto);
        then(validacaoSolicitacaoAdocao2).should().validar(dto);

    }


}