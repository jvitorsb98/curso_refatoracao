package br.com.alura.adopet.api.service.tutor;

import br.com.alura.adopet.api.dto.tutor.atualizar.AtualizarTutorDTO;
import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.tutor.validacoes.atualizar.ValidacaoAtuaizacaoTutor;
import br.com.alura.adopet.api.service.tutor.validacoes.register.ValidacaoRegistroTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private List<ValidacaoRegistroTutor> validacaoRegistroTutorList;

    @Autowired
    private List<ValidacaoAtuaizacaoTutor> validacaoAtuaizacaoTutorList;

    public void registro(TutorRegistroDTO tutorRegistroDTO){
        validacaoRegistroTutorList.forEach(v -> v.validation(tutorRegistroDTO));
        Tutor tutor = new Tutor(tutorRegistroDTO);
        tutorRepository.save(tutor);
    }

    public void atualizar(Long idTutor, AtualizarTutorDTO atualizarTutorDTO) {
        validacaoAtuaizacaoTutorList.forEach(v -> v.validacao(idTutor));
        Tutor tutor = tutorRepository.getReferenceById(idTutor);
        tutor.atualiza(atualizarTutorDTO);
    }
}
