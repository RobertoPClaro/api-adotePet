package br.com.adotePet.api_adotePet.repository;

import br.com.adotePet.api_adotePet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
