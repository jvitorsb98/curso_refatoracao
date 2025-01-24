package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdocaoService {


    @Autowired
    private EmailService emailService;

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;


    public void solicitar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) throws ValidationException {
        Pet pet = petRepository.getReferenceById(solicitacaoAdocaoDTO.idPet());
        Tutor tutor = tutorRepository.getReferenceById(solicitacaoAdocaoDTO.idTutor());
        if (pet.getAdotado() == true) {
            throw new ValidacaoException("Pet já foi adotado");
        } else {
            List<Adocao> adocoes = repository.findAll();
            for (Adocao a : adocoes) {
                if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
                }
            }
            for (Adocao a : adocoes) {
                if (a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
                }
            }
            for (Adocao a : adocoes) {
                int contador = 0;
                if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.APROVADO) {
                    contador = contador + 1;
                }
                if (contador == 5) {
                    throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
                }
            }
        }
        Adocao adocao = new Adocao();
        adocao.setData(LocalDateTime.now());
        adocao.setPet(pet);
        adocao.setTutor(tutor);
        adocao.setMotivo(solicitacaoAdocaoDTO.motivo());
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        repository.save(adocao);

        emailService.send(adocao.getPet().getAbrigo().getEmail() ,
                "Solicitação de adoção",
                "Olá " +adocao.getPet().getAbrigo().getNome() +"!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +adocao.getPet().getNome() +". \nFavor avaliar para aprovação ou reprovação.");

    }

    public void aprovar(AprovacaoAdocaoDTO aprovacaoAdocaoDTO){
        Adocao adocao = repository.getReferenceById(aprovacaoAdocaoDTO.idAdocao());
        adocao.setStatus(StatusAdocao.APROVADO);
        emailService.send(adocao.getTutor().getEmail(),
                "Adoção aprovada",
                "Parabéns " +adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.");
    }

    public void reprovar(ReprovacaoAdocaoDTO reprovacaoAdocao){
        Adocao adocao = repository.getReferenceById(reprovacaoAdocao.idAdocao());
        adocao.setStatus(StatusAdocao.REPROVADO);
        emailService.send(adocao.getTutor().getEmail(),
                "Adoção reprovada",
                "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus());

    }


}
