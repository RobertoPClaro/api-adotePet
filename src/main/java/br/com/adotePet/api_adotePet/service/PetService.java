package br.com.adotePet.api_adotePet.service;

import br.com.adotePet.api_adotePet.dto.Pet.CadastroPetDto;
import br.com.adotePet.api_adotePet.dto.Pet.DetalhesPetDto;
import br.com.adotePet.api_adotePet.entity.Abrigo;
import br.com.adotePet.api_adotePet.entity.Pet;
import br.com.adotePet.api_adotePet.repository.AbrigoRepository;
import br.com.adotePet.api_adotePet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private AbrigoRepository abrigoRepository;

    public List<DetalhesPetDto> buscarPetsDisponiveis() {
        return petRepository
                .findAllByAdotadoFalse()
                .stream()
                .map(DetalhesPetDto::new)
                .toList();
    }

    public void cadastrarPet( CadastroPetDto dto) {
        Abrigo abrigo = abrigoRepository.getReferenceById(dto.abrigoId());
        petRepository.save(new Pet(dto, abrigo));
    }
}
