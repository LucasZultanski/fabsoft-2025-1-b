package br.univille.projfabsofttotemmuseum.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.projfabsofttotemmuseum.entity.Checkup;
import br.univille.projfabsofttotemmuseum.repository.CheckupRepository;
import br.univille.projfabsofttotemmuseum.service.CheckupService;

@Service
public class CheckupServiceImpl implements CheckupService {

    @Autowired
    private CheckupRepository repository;

    @Override
    public Checkup save(Checkup checkup) {
        repository.save(checkup);
        return checkup;
    }

    @Override
    public List<Checkup> getAllCheckups() {
        return repository.findAll();
    }

    @Override
    public Checkup getCheckupById(Long id) {
        Optional<Checkup> checkup = repository.findById(id);
        return checkup.orElse(null);
    }

    @Override
    public Checkup delete(Long id) {
        Optional<Checkup> checkup = repository.findById(id);
        if (checkup.isPresent()) {
            repository.delete(checkup.get());
            return checkup.get();
        }
        return null;
    }
}