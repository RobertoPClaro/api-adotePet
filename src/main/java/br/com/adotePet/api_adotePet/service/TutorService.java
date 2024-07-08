package br.com.adotePet.api_adotePet.service;

import br.com.adotePet.api_adotePet.dto.Pet.DetalhesPetDto;
import br.com.adotePet.api_adotePet.dto.Tutor.AtualizacaoTutorDto;
import br.com.adotePet.api_adotePet.dto.Tutor.CadastroTutorDto;
import br.com.adotePet.api_adotePet.dto.Tutor.DetalhesTutorDto;
import br.com.adotePet.api_adotePet.entity.Tutor;
import br.com.adotePet.api_adotePet.excpetion.ValidacaoException;
import br.com.adotePet.api_adotePet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;

    public void cadastrar(CadastroTutorDto dto) {
        boolean jaCadastrado = tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email());

        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }

        tutorRepository.save(new Tutor(dto));
    }

    public void atualizar(AtualizacaoTutorDto dto) {
        Tutor tutor = tutorRepository.getReferenceById(dto.id());
        tutor.atualizarDados(dto);
    }

    public List<DetalhesTutorDto> listarTutores() {
        List<Tutor> tutores = tutorRepository.findAll();
        return tutores.stream()
                .map(DetalhesTutorDto::new)
                .toList();
    }
}
