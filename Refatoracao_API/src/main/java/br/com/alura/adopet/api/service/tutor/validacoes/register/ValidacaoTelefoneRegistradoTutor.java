package br.com.alura.adopet.api.service.tutor.validacoes.register;

import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTelefoneRegistradoTutor implements ValidacaoRegistroTutor{

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public void validation(TutorRegistroDTO tutorRegistroDTO) {
        if(tutorRepository.existsByEmail(tutorRegistroDTO.email())){
            throw new ValidacaoException("Telefone já cadastrado para outro tutor");
        }
    }
}
