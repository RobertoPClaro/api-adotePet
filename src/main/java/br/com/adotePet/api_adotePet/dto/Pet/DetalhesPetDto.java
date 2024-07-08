package br.com.adotePet.api_adotePet.dto.Pet;

import br.com.adotePet.api_adotePet.entity.Pet;
import br.com.adotePet.api_adotePet.entity.enums.TipoPet;

public record DetalhesPetDto(
        Long id,
        TipoPet tipo,
        String nome,
        String raca,
        Integer idade) {
    public DetalhesPetDto(Pet pet) {
        this(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade());
    }
}
