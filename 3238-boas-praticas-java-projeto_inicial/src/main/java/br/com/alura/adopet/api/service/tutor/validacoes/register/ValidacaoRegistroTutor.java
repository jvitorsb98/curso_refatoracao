package br.com.alura.adopet.api.service.tutor.validacoes.register;

import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import org.springframework.stereotype.Component;

@Component
public interface ValidacaoRegistroTutor {

    void validation(TutorRegistroDTO tutorRegistroDTO);

}
