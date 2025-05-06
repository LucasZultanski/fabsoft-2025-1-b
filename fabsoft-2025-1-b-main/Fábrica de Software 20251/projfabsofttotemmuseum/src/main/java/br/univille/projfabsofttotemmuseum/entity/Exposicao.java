package br.univille.projfabsofttotemmuseum.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Exposicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome; // Nome da exposição

    @Column(nullable = false)
    private String nomeArtista; // Nome do artista

    @Column(nullable = false)
    private String descricao; // Descrição da obra

    @Column(nullable = false)
    private LocalDateTime inicioExposicao; // Data e hora de início da exposição

    @Column(nullable = false)
    private LocalDateTime fimExposicao; // Data e hora de término da exposição

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario; // Relacionamento com Usuario

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getInicioExposicao() {
        return inicioExposicao;
    }

    public void setInicioExposicao(LocalDateTime inicioExposicao) {
        this.inicioExposicao = inicioExposicao;
    }

    public LocalDateTime getFimExposicao() {
        return fimExposicao;
    }

    public void setFimExposicao(LocalDateTime fimExposicao) {
        this.fimExposicao = fimExposicao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}