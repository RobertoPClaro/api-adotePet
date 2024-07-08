package br.com.adotePet.api_adotePet.controller;

import br.com.adotePet.api_adotePet.dto.Tutor.AtualizacaoTutorDto;
import br.com.adotePet.api_adotePet.dto.Tutor.CadastroTutorDto;
import br.com.adotePet.api_adotePet.dto.Tutor.DetalhesTutorDto;
import br.com.adotePet.api_adotePet.entity.Tutor;
import br.com.adotePet.api_adotePet.excpetion.ValidacaoException;
import br.com.adotePet.api_adotePet.repository.TutorRepository;
import br.com.adotePet.api_adotePet.service.TutorService;
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
    private TutorService tutorService;

    @GetMapping
    public ResponseEntity<List<DetalhesTutorDto>> listarTutores(){
        List<DetalhesTutorDto> tutores = tutorService.listarTutores();
        return ResponseEntity.ok(tutores);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroTutorDto dto) {
        try {
            tutorService.cadastrar(dto);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizacaoTutorDto dto) {
        try {
            tutorService.atualizar(dto);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
