package br.com.adotePet.api_adotePet.repository;

import br.com.adotePet.api_adotePet.entity.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    boolean existsByTelefone(String telefone);

    boolean existsByNome(String nome);

    boolean existsByEmail(String email);

    Abrigo findByNome(String idOuNome);
}
