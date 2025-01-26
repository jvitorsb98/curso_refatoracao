package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.abrigo.details.AbrigoDetailsDTO;
import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.dto.pets.register.RegistroPetDTO;
import br.com.alura.adopet.api.service.abrigo.AbrigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável por gerenciar as operações relacionadas aos abrigos.
 */
@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    /**
     * Endpoint para cadastrar um novo abrigo.
     *
     * @param abrigoRegisterDTO Dados do abrigo a ser cadastrado.
     * @return Resposta HTTP 200 (OK) se o cadastro for realizado com sucesso.
     */
    @Operation(summary = "Cadastrar um novo abrigo", description = "Registra um novo abrigo no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody(description = "Dados para o registro de um abrigo",
            required = true,
            content = @Content(schema = @Schema(implementation = AbrigoRegisterDTO.class)))
                                            @Valid AbrigoRegisterDTO abrigoRegisterDTO) {
        abrigoService.registrar(abrigoRegisterDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para listar abrigos cadastrados.
     *
     * @param pageable Paginação dos resultados.
     * @return Lista paginada de detalhes dos abrigos.
     */
    @Operation(summary = "Listar abrigos", description = "Retorna uma lista paginada com os detalhes dos abrigos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de abrigos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<AbrigoDetailsDTO>> listar(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(abrigoService.listar(pageable), HttpStatus.OK);
    }

    /**
     * Endpoint para listar pets de um abrigo específico.
     *
     * @param idOuNome Identificador (ID ou nome) do abrigo.
     * @param pageable Paginação dos resultados.
     * @return Lista paginada de detalhes dos pets.
     */
    @Operation(summary = "Listar pets de um abrigo", description = "Retorna uma lista paginada de pets associados a um abrigo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pets retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<Page<PetDetailsDTO>> listarPets(
            @PathVariable String idOuNome,
            @PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(abrigoService.listarPets(pageable, idOuNome), HttpStatus.OK);
    }

    /**
     * Endpoint para cadastrar um pet em um abrigo.
     *
     * @param idOuNome        Identificador (ID ou nome) do abrigo.
     * @param registroPetDTO  Dados do pet a ser cadastrado.
     * @return Resposta HTTP 200 (OK) se o cadastro for realizado com sucesso.
     */
    @Operation(summary = "Cadastrar um pet em um abrigo", description = "Registra um novo pet associado a um abrigo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(
            @PathVariable String idOuNome,
            @RequestBody(description = "Dados para o registro de um pet",
                    required = true,
                    content = @Content(schema = @Schema(implementation = RegistroPetDTO.class)))
            @Valid RegistroPetDTO registroPetDTO) {
        abrigoService.registrarPet(registroPetDTO, idOuNome);
        return ResponseEntity.ok().build();
    }
}
