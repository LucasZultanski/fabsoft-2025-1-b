package br.univille.projfabsofttotemmuseum.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int nota; // Nota de 0 a 10

    private LocalDateTime dataHora; // Data e hora da avaliação
    
    @Column(nullable = false)
    private String genero; // Gênero do usuário que fez a avaliação

    @Column(nullable = false)
    private int idade; // Idade do usuário que fez a avaliação

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}