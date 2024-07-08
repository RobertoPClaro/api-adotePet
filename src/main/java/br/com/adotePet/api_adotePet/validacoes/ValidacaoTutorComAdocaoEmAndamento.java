package br.com.adotePet.api_adotePet.validacoes;

import br.com.adotePet.api_adotePet.dto.Adocao.SolicitacaoAdocaoDto;
import br.com.adotePet.api_adotePet.entity.Adocao;
import br.com.adotePet.api_adotePet.entity.Tutor;
import br.com.adotePet.api_adotePet.entity.enums.StatusAdocao;
import br.com.adotePet.api_adotePet.excpetion.ValidacaoException;
import br.com.adotePet.api_adotePet.repository.AdocaoRepository;
import br.com.adotePet.api_adotePet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements IValidacaoSolicitacaoAdocao{
    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = adocaoRepository.findAll();
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());
        for (Adocao a : adocoes) {
            if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
            }
        }
    }
}
