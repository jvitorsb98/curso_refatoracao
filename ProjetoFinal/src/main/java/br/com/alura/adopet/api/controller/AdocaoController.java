package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.service.adocao.AdocaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável por gerenciar adoções.
 */
@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private AdocaoService adocaoService;

    /**
     * Endpoint para solicitar uma adoção.
     *
     * @param solicitacaoAdocaoDTO Dados da solicitação de adoção.
     * @return Resposta HTTP 200 (OK) se a solicitação for realizada com sucesso.
     */
    @Operation(summary = "Solicitar adoção", description = "Cria uma nova solicitação de adoção.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(
            @RequestBody(description = "Dados para solicitar adoção",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SolicitacaoAdocaoDTO.class)))
            @Valid SolicitacaoAdocaoDTO solicitacaoAdocaoDTO) {
        this.adocaoService.solicitar(solicitacaoAdocaoDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para aprovar uma adoção.
     *
     * @param aprovacaoAdocaoDTO Dados para aprovação da adoção.
     * @return Resposta HTTP 200 (OK) se a adoção for aprovada com sucesso.
     */
    @Operation(summary = "Aprovar adoção", description = "Aprova uma solicitação de adoção pendente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adoção aprovada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(
            @RequestBody(description = "Dados para aprovação da adoção",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AprovacaoAdocaoDTO.class)))
            @Valid AprovacaoAdocaoDTO aprovacaoAdocaoDTO) {
        this.adocaoService.aprovar(aprovacaoAdocaoDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para reprovar uma adoção.
     *
     * @param reprovacaoAdocaoDTO Dados para reprovação da adoção.
     * @return Resposta HTTP 200 (OK) se a adoção for reprovada com sucesso.
     */
    @Operation(summary = "Reprovar adoção", description = "Reprova uma solicitação de adoção pendente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adoção reprovada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(
            @RequestBody(description = "Dados para reprovação da adoção",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ReprovacaoAdocaoDTO.class)))
            @Valid ReprovacaoAdocaoDTO reprovacaoAdocaoDTO) {
        this.adocaoService.reprovar(reprovacaoAdocaoDTO);
        return ResponseEntity.ok().build();
    }
}
