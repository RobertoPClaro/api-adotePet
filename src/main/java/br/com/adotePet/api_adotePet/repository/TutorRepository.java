package br.com.adotePet.api_adotePet.repository;

import br.com.adotePet.api_adotePet.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByTelefone(String telefone);

    boolean existsByEmail(String email);
}
