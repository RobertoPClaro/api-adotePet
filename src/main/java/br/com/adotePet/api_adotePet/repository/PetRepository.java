package br.com.adotePet.api_adotePet.repository;

import br.com.adotePet.api_adotePet.entity.Abrigo;
import br.com.adotePet.api_adotePet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByAdotadoFalse();

    List<Pet> findByAbrigo(Abrigo abrigo);
}
