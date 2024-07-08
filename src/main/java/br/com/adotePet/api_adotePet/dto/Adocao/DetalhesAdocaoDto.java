package br.com.adotePet.api_adotePet.dto.Adocao;

import br.com.adotePet.api_adotePet.entity.Adocao;
import br.com.adotePet.api_adotePet.entity.Pet;
import br.com.adotePet.api_adotePet.entity.Tutor;
import br.com.adotePet.api_adotePet.entity.enums.StatusAdocao;

import java.time.LocalDateTime;

public record DetalhesAdocaoDto(
        Long id,
        LocalDateTime data,
        Long tutorId,
        Long petId,
        String motivo,
        StatusAdocao status,
        String justificativaStatus
) {
    public DetalhesAdocaoDto(Adocao adocao){
        this(adocao.getId(), adocao.getData(), adocao.getTutor().getId(), adocao.getPet().getId(), adocao.getMotivo(), adocao.getStatus(), adocao.getJustificativaStatus());
    }
}
