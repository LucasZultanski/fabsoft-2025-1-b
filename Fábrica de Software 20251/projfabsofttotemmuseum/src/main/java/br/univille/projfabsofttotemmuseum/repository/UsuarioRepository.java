package br.univille.projfabsofttotemmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.projfabsofttotemmuseum.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}
