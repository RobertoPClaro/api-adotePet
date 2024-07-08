package br.com.adotePet.api_adotePet.dto.Pet;

import br.com.adotePet.api_adotePet.entity.enums.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroPetDto(
            @NotNull
            Long abrigoId,
            @NotNull
            TipoPet tipo,
            @NotBlank
            String nome,
            @NotBlank
            String raca,
            @NotNull
            Integer idade,
            @NotBlank
            String cor,
            @NotNull
            Float peso) {
}
