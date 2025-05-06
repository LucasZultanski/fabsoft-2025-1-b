package br.univille.projfabsofttotemmuseum.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.projfabsofttotemmuseum.entity.Avaliacao;
import br.univille.projfabsofttotemmuseum.repository.AvaliacaoRepository;
import br.univille.projfabsofttotemmuseum.service.AvaliacaoService;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    @Override
    public Avaliacao save(Avaliacao avaliacao) {
        repository.save(avaliacao);
        return avaliacao;
    }

    @Override
    public List<Avaliacao> getAllAvaliacoes() {
        return repository.findAll();
    }

    @Override
    public Avaliacao getAvaliacaoById(Long id) {
        Optional<Avaliacao> avaliacao = repository.findById(id);
        return avaliacao.orElse(null);
    }

    @Override
    public Avaliacao delete(Long id) {
        Optional<Avaliacao> avaliacao = repository.findById(id);
        if (avaliacao.isPresent()) {
            repository.delete(avaliacao.get());
            return avaliacao.get();
        }
        return null;
    }
}