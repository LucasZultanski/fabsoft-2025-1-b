package br.univille.projfabsofttotemmuseum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.projfabsofttotemmuseum.entity.Usuario;
import br.univille.projfabsofttotemmuseum.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        var listaUsuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }
        if (usuario.getId() == null || usuario.getId() == 0) {
            usuarioService.save(usuario);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
        if (id <= 0 || usuario == null) {
            return ResponseEntity.badRequest().build();
        }
        var usuarioAntigo = usuarioService.getUsuarioById(id);
        if (usuarioAntigo == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioAntigo.setEmail(usuario.getEmail());
        usuarioAntigo.setTelefone(usuario.getTelefone());
        usuarioAntigo.setNotificacoesExposicoes(usuario.isNotificacoesExposicoes());
        usuarioAntigo.setNotificacoesEventos(usuario.isNotificacoesEventos());
        usuarioAntigo.setNotificacoesNovidades(usuario.isNotificacoesNovidades());

        usuarioService.save(usuarioAntigo);
        return new ResponseEntity<Usuario>(usuarioAntigo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var usuarioExcluido = usuarioService.getUsuarioById(id);
        if (usuarioExcluido == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.delete(id);

        return new ResponseEntity<Usuario>(usuarioExcluido, HttpStatus.OK);
    }
}
