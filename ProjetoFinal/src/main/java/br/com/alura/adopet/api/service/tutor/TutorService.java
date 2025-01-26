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

/**
 * Serviço responsável pela lógica de negócio relacionada ao cadastro e atualização
 * de tutores. Inclui validações antes de persistir os dados no repositório.
 *
 * <p>Esta classe fornece métodos para registrar um novo tutor e para atualizar
 * os dados de um tutor existente. Antes de realizar essas operações, são
 * executadas as validações necessárias para garantir que as informações
 * fornecidas estão corretas e não violam regras de negócio.</p>
 */
@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private List<ValidacaoRegistroTutor> validacaoRegistroTutorList;

    @Autowired
    private List<ValidacaoAtuaizacaoTutor> validacaoAtuaizacaoTutorList;

    /**
     * Registra um novo tutor no sistema.
     *
     * <p>Antes de persistir o tutor, são realizadas as validações definidas
     * nas implementações da interface {@link ValidacaoRegistroTutor}.</p>
     *
     * @param tutorRegistroDTO Dados do tutor a serem registrados.
     */
    public void registro(TutorRegistroDTO tutorRegistroDTO){
        validacaoRegistroTutorList.forEach(v -> v.validation(tutorRegistroDTO));
        Tutor tutor = new Tutor(tutorRegistroDTO);
        tutorRepository.save(tutor);
    }

    /**
     * Atualiza os dados de um tutor existente.
     *
     * <p>Antes de realizar a atualização, são executadas as validações
     * definidas nas implementações da interface {@link ValidacaoAtuaizacaoTutor}.</p>
     *
     * @param idTutor Identificador do tutor a ser atualizado.
     * @param atualizarTutorDTO Dados atualizados do tutor.
     */
    public void atualizar(Long idTutor, AtualizarTutorDTO atualizarTutorDTO) {
        validacaoAtuaizacaoTutorList.forEach(v -> v.validacao(idTutor));
        Tutor tutor = tutorRepository.getReferenceById(idTutor);
        tutor.atualiza(atualizarTutorDTO);
    }
}
