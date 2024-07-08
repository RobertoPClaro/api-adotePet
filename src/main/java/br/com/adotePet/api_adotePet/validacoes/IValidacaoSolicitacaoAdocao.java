package br.com.adotePet.api_adotePet.validacoes;

import br.com.adotePet.api_adotePet.dto.Adocao.SolicitacaoAdocaoDto;

public interface IValidacaoSolicitacaoAdocao {
    void validar(SolicitacaoAdocaoDto dto);
}
