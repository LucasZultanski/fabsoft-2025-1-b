package br.univille.projfabsofttotemmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.projfabsofttotemmuseum.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Avaliacao> findByNota(int nota);
}