package br.univille.projfabsofttotemmuseum.service;

import java.util.List;

import br.univille.projfabsofttotemmuseum.entity.Checkup;

public interface CheckupService {
    Checkup save(Checkup checkup);
    List<Checkup> getAllCheckups();
    Checkup getCheckupById(Long id);
    Checkup delete(Long id);
}