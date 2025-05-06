package br.univille.projfabsofttotemmuseum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.univille.projfabsofttotemmuseum.entity.Avaliacao;
import br.univille.projfabsofttotemmuseum.service.AvaliacaoService;

@RestController
@RequestMapping("/api/v1/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<Avaliacao>> getAvaliacoes() {
        var listaAvaliacoes = avaliacaoService.getAllAvaliacoes();
        return new ResponseEntity<>(listaAvaliacoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable Long id) {
        var avaliacao = avaliacaoService.getAvaliacaoById(id);
        if (avaliacao == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(avaliacao, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Avaliacao> postAvaliacao(@RequestBody Avaliacao avaliacao) {
        if (avaliacao == null || avaliacao.getUsuario() == null) {
            return ResponseEntity.badRequest().build();
        }
        var novaAvaliacao = avaliacaoService.save(avaliacao);
        return new ResponseEntity<>(novaAvaliacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> putAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        if (id <= 0 || avaliacao == null || avaliacao.getUsuario() == null) {
            return ResponseEntity.badRequest().build();
        }
        var avaliacaoExistente = avaliacaoService.getAvaliacaoById(id);
        if (avaliacaoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        avaliacaoExistente.setNota(avaliacao.getNota());
        avaliacaoExistente.setDataHora(avaliacao.getDataHora());
        avaliacaoExistente.setUsuario(avaliacao.getUsuario());

        var avaliacaoAtualizada = avaliacaoService.save(avaliacaoExistente);
        return new ResponseEntity<>(avaliacaoAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Avaliacao> deleteAvaliacao(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        var avaliacao = avaliacaoService.getAvaliacaoById(id);
        if (avaliacao == null) {
            return ResponseEntity.notFound().build();
        }
        avaliacaoService.delete(id);
        return new ResponseEntity<>(avaliacao, HttpStatus.OK);
    }
}