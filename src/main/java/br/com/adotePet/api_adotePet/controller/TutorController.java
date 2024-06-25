package br.com.adotePet.api_adotePet.controller;

import br.com.adotePet.api_adotePet.entity.Tutor;
import br.com.adotePet.api_adotePet.repository.TutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    @GetMapping
    public ResponseEntity<List<Tutor>> listarTodosTutores(){
        List<Tutor> tutores = tutorRepository.findAll();
        return ResponseEntity.ok(tutores);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid Tutor tutor){
        boolean telefoneJaCadastrado = tutorRepository.existsByTelefone(tutor.getTelefone());
        boolean emailJaCadastrado = tutorRepository.existsByEmail(tutor.getEmail());

        if(telefoneJaCadastrado || emailJaCadastrado) {
            return ResponseEntity.badRequest().body("Dados já cadastrados para outro tutor!");
        } else {
            tutorRepository.save(tutor);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid Tutor tutor){
        tutorRepository.save(tutor);
        return ResponseEntity.ok().build();
    }
}
