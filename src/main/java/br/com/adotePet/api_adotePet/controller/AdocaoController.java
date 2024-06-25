package br.com.adotePet.api_adotePet.controller;

import br.com.adotePet.api_adotePet.entity.Adocao;
import br.com.adotePet.api_adotePet.entity.enums.StatusAdocao;
import br.com.adotePet.api_adotePet.repository.AdocaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid Adocao adocao){
        if (adocao.getPet().getAdotado() == true) {
            return ResponseEntity.badRequest().body("Pet já foi adotado!");
        } else {
            List<Adocao> adocoes = adocaoRepository.findAll();
            for (Adocao a : adocoes) {
                if (a.getTutor() == adocao.getTutor() && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    return ResponseEntity.badRequest().body("Tutor já possui outra adoção aguardando avaliação!");
                }
            }
            for (Adocao a : adocoes) {
                if (a.getPet() == adocao.getPet() && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                    return ResponseEntity.badRequest().body("Pet já está aguardando avaliação para ser adotado!");
                }
            }
            for (Adocao a : adocoes) {
                int contador = 0;
                if (a.getTutor() == adocao.getTutor() && a.getStatus() == StatusAdocao.APROVADO) {
                    contador = contador + 1;
                }
                if (contador == 5) {
                    return ResponseEntity.badRequest().body("Tutor chegou ao limite máximo de 5 adoções!");
                }
            }
        }
        adocao.setData(LocalDateTime.now());
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        adocaoRepository.save(adocao);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid Adocao adocao){
        adocao.setStatus(StatusAdocao.APROVADO);
        adocaoRepository.save(adocao);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid Adocao adocao) {
        adocao.setStatus(StatusAdocao.REPROVADO);
        adocaoRepository.save(adocao);

        return ResponseEntity.ok().build();
    }
}
