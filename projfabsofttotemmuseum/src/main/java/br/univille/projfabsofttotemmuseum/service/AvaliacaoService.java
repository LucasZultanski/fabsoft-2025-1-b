package br.univille.projfabsofttotemmuseum.service;

import java.util.List;

import br.univille.projfabsofttotemmuseum.entity.Avaliacao;

public interface AvaliacaoService {
    Avaliacao save(Avaliacao avaliacao);
    List<Avaliacao> getAllAvaliacoes();
    Avaliacao getAvaliacaoById(Long id);
    Avaliacao delete(Long id);
}