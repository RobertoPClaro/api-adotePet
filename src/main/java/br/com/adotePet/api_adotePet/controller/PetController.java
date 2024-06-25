package br.com.adotePet.api_adotePet.controller;

import br.com.adotePet.api_adotePet.entity.Pet;
import br.com.adotePet.api_adotePet.repository.PetRepository;
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
    private PetRepository petRepository;


    @GetMapping
    public ResponseEntity<List<Pet>> listarTodosDisponiveis() {
        List<Pet> pets = petRepository.findAll();
        List<Pet> disponiveis = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getAdotado() == false) {
                disponiveis.add(pet);
            }
        }
        return ResponseEntity.ok(disponiveis);
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid Pet pet){

        petRepository.save(pet);
        return ResponseEntity.ok().build();
    }
}
