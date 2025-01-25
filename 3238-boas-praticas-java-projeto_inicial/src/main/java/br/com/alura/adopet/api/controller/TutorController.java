package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.tutor.atualizar.AtualizarTutorDTO;
import br.com.alura.adopet.api.dto.tutor.register.TutorRegistroDTO;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.service.tutor.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid TutorRegistroDTO tutorRegistroDTO) {
        tutorService.registro(tutorRegistroDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@PathVariable Long idTutor,@RequestBody @Valid AtualizarTutorDTO atualizarTutorDTO) {
        tutorService.atualizar(idTutor,atualizarTutorDTO);
        return ResponseEntity.ok().build();
    }

}
