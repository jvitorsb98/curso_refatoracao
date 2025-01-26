package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.service.pet.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável por gerenciar os pets disponíveis para adoção.
 */
@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    /**
     * Endpoint para listar todos os pets disponíveis para adoção.
     *
     * @param pageable Objeto para paginação e ordenação dos resultados.
     * @return Página com os detalhes dos pets disponíveis.
     */
    @Operation(summary = "Listar pets disponíveis", description = "Retorna uma lista paginada de todos os pets disponíveis para adoção.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PetDetailsDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<PetDetailsDTO>> listarTodosDisponiveis(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<PetDetailsDTO> petsDisponiveis = petService.listarTodosDisponiveis(pageable);
        return ResponseEntity.ok(petsDisponiveis);
    }
}
