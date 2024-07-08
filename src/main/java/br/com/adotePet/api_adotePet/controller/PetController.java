package br.com.adotePet.api_adotePet.controller;

import br.com.adotePet.api_adotePet.dto.Pet.CadastroPetDto;
import br.com.adotePet.api_adotePet.dto.Pet.DetalhesPetDto;
import br.com.adotePet.api_adotePet.entity.Abrigo;
import br.com.adotePet.api_adotePet.entity.Pet;
import br.com.adotePet.api_adotePet.repository.PetRepository;
import br.com.adotePet.api_adotePet.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<DetalhesPetDto>> listarTodosDisponiveis() {
        List<DetalhesPetDto> pets = petService.buscarPetsDisponiveis();
        return ResponseEntity.ok(pets);
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroPetDto dto){
        petService.cadastrarPet(dto);
        return ResponseEntity.ok().build();
    }
}
