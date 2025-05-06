package br.univille.projfabsofttotemmuseum.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String telefone; // Opcional

    private boolean notificacoesExposicoes;
    private boolean notificacoesEventos;
    private boolean notificacoesNovidades;

    private Integer idade; // Idade do usuário
    private String genero; // Gênero do usuário

    @ManyToMany(mappedBy = "usuariosNotificados")
    private List<Evento> eventosNotificados;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes; // Relacionamento com Avaliacao

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Checkup> checkups; // Relacionamento com Checkup

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exposicao> exposicoes; // Relacionamento com Exposicao

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isNotificacoesExposicoes() {
        return notificacoesExposicoes;
    }

    public void setNotificacoesExposicoes(boolean notificacoesExposicoes) {
        this.notificacoesExposicoes = notificacoesExposicoes;
    }

    public boolean isNotificacoesEventos() {
        return notificacoesEventos;
    }

    public void setNotificacoesEventos(boolean notificacoesEventos) {
        this.notificacoesEventos = notificacoesEventos;
    }

    public boolean isNotificacoesNovidades() {
        return notificacoesNovidades;
    }

    public void setNotificacoesNovidades(boolean notificacoesNovidades) {
        this.notificacoesNovidades = notificacoesNovidades;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Evento> getEventosNotificados() {
        return eventosNotificados;
    }

    public void setEventosNotificados(List<Evento> eventosNotificados) {
        this.eventosNotificados = eventosNotificados;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Checkup> getCheckups() {
        return checkups;
    }

    public void setCheckups(List<Checkup> checkups) {
        this.checkups = checkups;
    }

    public List<Exposicao> getExposicoes() {
        return exposicoes;
    }

    public void setExposicoes(List<Exposicao> exposicoes) {
        this.exposicoes = exposicoes;
    }
}