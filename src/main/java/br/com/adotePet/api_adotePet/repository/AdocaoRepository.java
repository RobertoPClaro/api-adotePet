package br.com.adotePet.api_adotePet.repository;

import br.com.adotePet.api_adotePet.entity.Adocao;
import br.com.adotePet.api_adotePet.entity.enums.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    boolean existsByPetIdAndStatus(Long idPet, StatusAdocao status);
}
