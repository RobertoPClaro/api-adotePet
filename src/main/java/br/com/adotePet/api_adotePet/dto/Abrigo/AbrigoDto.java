package br.com.adotePet.api_adotePet.dto.Abrigo;

import br.com.adotePet.api_adotePet.entity.Abrigo;

public record AbrigoDto(Long id, String nome) {

    public AbrigoDto(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome());
    }

}