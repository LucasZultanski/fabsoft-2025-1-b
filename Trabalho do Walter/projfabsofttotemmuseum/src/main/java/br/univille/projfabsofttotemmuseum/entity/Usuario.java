package br.univille.projfabsofttotemmuseum.entity;

import jakarta.persistence.*;

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
}