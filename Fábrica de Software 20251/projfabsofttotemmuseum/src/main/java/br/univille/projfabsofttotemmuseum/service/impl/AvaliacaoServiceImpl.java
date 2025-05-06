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

package br.univille.projfabsofttotemmuseum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email(message = "O e-mail deve ser válido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter 10 ou 11 dígitos")
    private String telefone; // Opcional

    private boolean notificacoesExposicoes;
    private boolean notificacoesEventos;
    private boolean notificacoesNovidades;

    @Min(value = 0, message = "A idade deve ser um número positivo")
    private Integer idade; // Idade do usuário

    private String genero; // Gênero do usuário (opcional)

    @ManyToMany(mappedBy = "usuariosNotificados")
    private List<Evento> eventosNotificados;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Checkup> checkups;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exposicao> exposicoes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacao> notificacoes;

    // Getters e Setters
}