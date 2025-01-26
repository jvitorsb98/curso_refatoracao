package br.com.alura.adopet.api.service.adocao;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.email.EmailService;
import br.com.alura.adopet.api.service.adocao.validacoes.register.*;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private List<ValidacaoSolicitacaoAdocao> validacaoSolicitacaoAdocaoList;

    public void solicitar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) throws ValidationException {
        // Validação das solicitações de adoção
        validacaoSolicitacaoAdocaoList.forEach(v -> v.validar(solicitacaoAdocaoDTO));

        // Verificação do Pet e Tutor
        Pet pet = petRepository.findById(solicitacaoAdocaoDTO.idPet())
                .orElseThrow(() -> new ValidationException("Pet não encontrado."));
        Tutor tutor = tutorRepository.findById(solicitacaoAdocaoDTO.idTutor())
                .orElseThrow(() -> new ValidationException("Tutor não encontrado."));

        // Criação e salvamento da adoção
        Adocao adocao = new Adocao(tutor, pet, solicitacaoAdocaoDTO.motivo());
        repository.save(adocao);

        // Envio de e-mail de solicitação
        enviarEmailAprovacaoReprovacao(adocao, "Solicitação de adoção",
                "Olá " + adocao.getPet().getAbrigo().getNome() + "!\n\nUma solicitação de adoção foi registrada hoje para o pet: "
                        + adocao.getPet().getNome() + ". \nFavor avaliar para aprovação ou reprovação.");
    }

    public void aprovar(AprovacaoAdocaoDTO aprovacaoAdocaoDTO) {
        Adocao adocao = repository.findById(aprovacaoAdocaoDTO.idAdocao())
                .orElseThrow(() -> new ValidationException("Adoção não encontrada."));
        adocao.aprovar();

        // Envio de e-mail após aprovação
        enviarEmailAprovacaoReprovacao(adocao, "Adoção aprovada",
                "Parabéns " + adocao.getTutor().getNome() + "!\n\nSua adoção do pet " + adocao.getPet().getNome()
                        + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                        + ", foi aprovada.\nFavor entrar em contato com o abrigo " + adocao.getPet().getAbrigo().getNome()
                        + " para agendar a busca do seu pet.");
    }

    public void reprovar(ReprovacaoAdocaoDTO reprovacaoAdocao) {
        Adocao adocao = repository.findById(reprovacaoAdocao.idAdocao())
                .orElseThrow(() -> new ValidationException("Adoção não encontrada."));
        adocao.reprovar(reprovacaoAdocao.justificativa());

        // Envio de e-mail após reprovação
        enviarEmailAprovacaoReprovacao(adocao, "Adoção reprovada",
                "Olá " + adocao.getTutor().getNome() + "!\n\nInfelizmente sua adoção do pet " + adocao.getPet().getNome()
                        + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                        + ", foi reprovada pelo abrigo " + adocao.getPet().getAbrigo().getNome() + " com a seguinte justificativa: "
                        + adocao.getJustificativaStatus());
    }

    /**
     * Método auxiliar para enviar e-mails de aprovação ou reprovação.
     *
     * @param adocao Adoção para a qual o e-mail será enviado.
     * @param assunto Assunto do e-mail.
     * @param mensagem Corpo do e-mail.
     */
    private void enviarEmailAprovacaoReprovacao(Adocao adocao, String assunto, String mensagem) {
        emailService.send(adocao.getPet().getAbrigo().getEmail(), assunto, mensagem);
    }
}
