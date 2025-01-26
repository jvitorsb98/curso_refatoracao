package br.com.alura.adopet.api.service.abrigo.validacoes.register;

import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import org.springframework.stereotype.Component;

@Component
public interface ValidacaoCadastroAbrigo {

    void validation(AbrigoRegisterDTO abrigoRegisterDTO);

}
