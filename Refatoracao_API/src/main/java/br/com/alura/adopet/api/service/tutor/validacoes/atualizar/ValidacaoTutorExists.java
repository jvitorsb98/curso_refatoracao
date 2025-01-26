package br.com.alura.adopet.api.service.tutor.validacoes.atualizar;

import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorExists implements ValidacaoAtuaizacaoTutor{

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public void validacao(Long idTutor) {
        if(!tutorRepository.existsById(idTutor)){
            throw new ValidacaoException("Tutor informado para atualização não existe");
        }
    }
}
