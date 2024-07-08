package br.com.adotePet.api_adotePet.service;

import br.com.adotePet.api_adotePet.dto.Adocao.AprovacaoAdocaoDto;
import br.com.adotePet.api_adotePet.dto.Adocao.DetalhesAdocaoDto;
import br.com.adotePet.api_adotePet.dto.Adocao.ReprovacaoAdocaoDto;
import br.com.adotePet.api_adotePet.dto.Adocao.SolicitacaoAdocaoDto;
import br.com.adotePet.api_adotePet.entity.Adocao;
import br.com.adotePet.api_adotePet.entity.Pet;
import br.com.adotePet.api_adotePet.entity.Tutor;
import br.com.adotePet.api_adotePet.repository.AdocaoRepository;
import br.com.adotePet.api_adotePet.repository.PetRepository;
import br.com.adotePet.api_adotePet.repository.TutorRepository;
import br.com.adotePet.api_adotePet.validacoes.IValidacaoSolicitacaoAdocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdocaoService {
    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private List<IValidacaoSolicitacaoAdocao> validacoes;

    public List<DetalhesAdocaoDto> listarAdocoes() {
        System.out.println("Iniciando o método listarAdocoes");
        List<Adocao> adocoes = adocaoRepository.findAll();
        System.out.println("Adocoes recuperadas: " + adocoes.size());
        List<DetalhesAdocaoDto> detalhes = adocoes.stream().map(DetalhesAdocaoDto::new).toList();
        System.out.println("Detalhes mapeados: " + detalhes.size());
        return detalhes;
    }
    public void solicitar(SolicitacaoAdocaoDto dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());

        validacoes.forEach(v -> v.validar(dto));

        Adocao adocao = new Adocao(tutor, pet, dto.motivo());
        adocaoRepository.save(adocao);
    }

    public void aprovar(AprovacaoAdocaoDto dto) {
        Adocao adocao = adocaoRepository.getReferenceById(dto.idAdocao());
        adocao.marcarComoAprovada();
    }
    public void reprovar(ReprovacaoAdocaoDto dto) {
        Adocao adocao = adocaoRepository.getReferenceById(dto.idAdocao());
        adocao.marcarComoReprovada(dto.justificativa());
    }
}