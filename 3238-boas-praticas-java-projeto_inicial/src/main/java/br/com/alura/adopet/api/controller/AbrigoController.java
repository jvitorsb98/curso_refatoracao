package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.abrigo.details.AbrigoDetailsDTO;
import br.com.alura.adopet.api.dto.abrigo.register.AbrigoRegisterDTO;
import br.com.alura.adopet.api.dto.pets.details.PetDetailsDTO;
import br.com.alura.adopet.api.dto.pets.register.RegistroPetDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.abrigo.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid AbrigoRegisterDTO abrigoRegisterDTO) {
        abrigoService.registrar(abrigoRegisterDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<AbrigoDetailsDTO>> listar(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(abrigoService.listar(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<Page<PetDetailsDTO>> listarPets(@PathVariable String idOuNome, @PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(abrigoService.listarPets(pageable,idOuNome), HttpStatus.OK);
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid RegistroPetDTO registroPetDTO) {
        abrigoService.registrarPet(registroPetDTO,idOuNome);
        return ResponseEntity.ok().build();
    }

}
