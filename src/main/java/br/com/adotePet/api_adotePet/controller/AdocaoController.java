package br.com.adotePet.api_adotePet.controller;

import br.com.adotePet.api_adotePet.dto.AprovacaoAdocaoDto;
import br.com.adotePet.api_adotePet.dto.DetalhesAdocaoDto;
import br.com.adotePet.api_adotePet.dto.ReprovacaoAdocaoDto;
import br.com.adotePet.api_adotePet.dto.SolicitacaoAdocaoDto;
import br.com.adotePet.api_adotePet.entity.Adocao;
import br.com.adotePet.api_adotePet.entity.enums.StatusAdocao;
import br.com.adotePet.api_adotePet.repository.AdocaoRepository;
import br.com.adotePet.api_adotePet.service.AdocaoService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
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
    private AdocaoService adocaoService;

    @GetMapping
    public ResponseEntity<List<DetalhesAdocaoDto>> listarAdocoes(){
        List<DetalhesAdocaoDto> adocoes = adocaoService.listarAdocoes();
        return ResponseEntity.ok(adocoes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitacaoAdocaoDto dto){
        try {
            this.adocaoService.solicitar(dto);
            return ResponseEntity.ok("Adoção solciitada com sucesso!");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid AprovacaoAdocaoDto dto){
        this.adocaoService.aprovar(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid ReprovacaoAdocaoDto dto) {
        this.adocaoService.reprovar(dto);
        return ResponseEntity.ok().build();
    }
}
