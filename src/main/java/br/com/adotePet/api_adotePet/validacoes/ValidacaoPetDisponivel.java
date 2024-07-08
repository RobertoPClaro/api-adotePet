package br.com.adotePet.api_adotePet.validacoes;

import br.com.adotePet.api_adotePet.dto.Adocao.SolicitacaoAdocaoDto;
import br.com.adotePet.api_adotePet.entity.Pet;
import br.com.adotePet.api_adotePet.excpetion.ValidacaoException;
import br.com.adotePet.api_adotePet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements IValidacaoSolicitacaoAdocao{
    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!");
        }
    }
}
