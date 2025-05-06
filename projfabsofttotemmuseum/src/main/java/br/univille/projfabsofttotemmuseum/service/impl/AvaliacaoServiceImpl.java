package br.univille.projfabsofttotemmuseum.service.impl;

import java.time.LocalDateTime;
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
        // Define a data e hora atual caso não esteja definida
        if (avaliacao.getDataHora() == null) {
            avaliacao.setDataHora(LocalDateTime.now());
        }
        return repository.save(avaliacao);
    }

    @Override
    public List<Avaliacao> getAllAvaliacoes() {
        return repository.findAll();
    }

    @Override
    public Avaliacao getAvaliacaoById(Long id) {
        return repository.findById(id).orElse(null);
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