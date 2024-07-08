package br.com.adotePet.api_adotePet.dto.Tutor;

import br.com.adotePet.api_adotePet.entity.Adocao;
import br.com.adotePet.api_adotePet.entity.Tutor;

import java.util.List;

public record DetalhesTutorDto(
        Long id,
        String nome,
        String telefone,
        String email,
        List<Long> adocoesIds) {
    public DetalhesTutorDto(Tutor tutor) {
        this(
                tutor.getId(),
                tutor.getNome(),
                tutor.getTelefone(),
                tutor.getEmail(),
                tutor.getAdocoes().stream().map(Adocao::getId).toList()
        );
    }
}
