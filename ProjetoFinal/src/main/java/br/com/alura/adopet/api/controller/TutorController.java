package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.tutor.atualizar.AtualizarTutorDTO;
import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.service.tutor.TutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pela gestão dos tutores, incluindo cadastro e atualização de informações.
 */
@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    /**
     * Endpoint para cadastrar um novo tutor.
     *
     * @param tutorRegistroDTO Objeto contendo os dados para registrar um novo tutor.
     * @return Resposta HTTP com status 200 indicando sucesso.
     */
    @Operation(summary = "Cadastrar tutor", description = "Registra um novo tutor no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutor registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados fornecidos")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid TutorRegistroDTO tutorRegistroDTO) {
        tutorService.registro(tutorRegistroDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para atualizar as informações de um tutor existente.
     *
     * @param idTutor            ID do tutor a ser atualizado.
     * @param atualizarTutorDTO  Objeto contendo os dados para atualizar o tutor.
     * @return Resposta HTTP com status 200 indicando sucesso.
     */
    @Operation(summary = "Atualizar tutor", description = "Atualiza as informações de um tutor existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tutor não encontrado")
    })
    @PutMapping("/{idTutor}")
    @Transactional
    public ResponseEntity<String> atualizar(
            @PathVariable Long idTutor,
            @RequestBody @Valid AtualizarTutorDTO atualizarTutorDTO) {
        tutorService.atualizar(idTutor, atualizarTutorDTO);
        return ResponseEntity.ok().build();
    }

}
