package br.com.alura.adopet.api.service.abrigo.validacoes.register;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoNomeJaCadastradoAbrigo implements ValidacaoCadastroAbrigo{

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Override
    public void validation(AbrigoRegisterDTO abrigoRegisterDTO) {
        if(abrigoRepository.existsByNome(abrigoRegisterDTO.nome())){
            throw new ValidacaoException("Nome já cadastrado para outro abrigo");
        }
    }
}
