package br.com.alura.adopet.api.service.adocao.validacoes.register;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validations.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validação para garantir que um pet esteja disponível para adoção.
 *
 * <p>Essa validação verifica se o pet especificado na solicitação de adoção ainda está disponível.
 * Caso o pet já tenha sido adotado, a solicitação de adoção será rejeitada.</p>
 *
 * <p>A exceção {@link ValidacaoException} será lançada caso o pet já tenha sido adotado.</p>
 */
@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private PetRepository petRepository;

    /**
     * Verifica se o pet especificado na solicitação de adoção ainda está disponível para adoção.
     *
     * @param solicitacaoAdocaoDTO Objeto contendo os dados da solicitação de adoção,
     *                             incluindo o ID do pet.
     *
     * @throws ValidacaoException Se o pet já foi adotado, uma exceção será lançada.
     */
    public void validar(SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) {
        Pet pet = petRepository.getReferenceById(solicitacaoAdocaoDTO.idPet());
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado");
        }
    }
}
