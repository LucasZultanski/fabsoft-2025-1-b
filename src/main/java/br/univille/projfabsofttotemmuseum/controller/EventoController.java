package br.univille.projfabsofttotemmuseum.controller;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.univille.projfabsofttotemmuseum.entity.Evento;
import br.univille.projfabsofttotemmuseum.service.EventoService;
import br.univille.projfabsofttotemmuseum.repository.UsuarioRepository;
import br.univille.projfabsofttotemmuseum.entity.Usuario;
import br.univille.projfabsofttotemmuseum.service.WhatsAppService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired(required = false)
    private WhatsAppService whatsAppService;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @GetMapping
    public ResponseEntity<List<Evento>> getEventos() {
        var listaEventos = eventoService.getAllEventos();
        return new ResponseEntity<>(listaEventos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Evento> postEvento(@RequestBody Evento evento) {
        if (evento == null) {
            return ResponseEntity.badRequest().build();
        }
        if (evento.getId() == 0) {
            eventoService.save(evento);
            return new ResponseEntity<>(evento, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> putEvento(@PathVariable long id, @RequestBody Evento evento) {
        if (id <= 0 || evento == null) {
            return ResponseEntity.badRequest().build();
        }
        var eventoAntigo = eventoService.getEventoById(id);
        if (eventoAntigo == null) {
            return ResponseEntity.notFound().build();
        }
        eventoAntigo.setNome(evento.getNome());
        eventoAntigo.setDataHora(evento.getDataHora());
        eventoAntigo.setLocal(evento.getLocal());
        eventoAntigo.setUsuariosNotificados(evento.getUsuariosNotificados());

        eventoService.save(eventoAntigo);
        return new ResponseEntity<>(eventoAntigo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Evento> deleteEvento(@PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var eventoExcluido = eventoService.getEventoById(id);
        if (eventoExcluido == null) {
            return ResponseEntity.notFound().build();
        }
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/postar")
    public ResponseEntity<String> postarEvento(
            @RequestParam("title") String title,
            @RequestParam("text") String text,
            @RequestParam("dataInicio") String dataInicio,
            @RequestParam("dataFim") String dataFim,
            @RequestParam("local") String local,
            @RequestParam("image") MultipartFile imageFile) {
        try {
            // Salvar imagem na pasta ./eventos/
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path imagePath = Paths.get("./eventos/" + fileName);
            Files.createDirectories(imagePath.getParent());
            Files.write(imagePath, imageFile.getBytes());

            // Salvar evento no banco
            Evento evento = new Evento();
            evento.setTitulo(title);
            evento.setTexto(text);
            evento.setImagem(fileName);
            evento.setNome(title);
            evento.setLocal(local);
            // Parse datas
            java.time.LocalDateTime dataInicioDT = java.time.LocalDateTime.parse(dataInicio);
            java.time.LocalDateTime dataFimDT = java.time.LocalDateTime.parse(dataFim);
            evento.setDataHora(dataInicioDT); // ou outro campo para data inicial
            // Se tiver campo para data final, setar também
            eventoService.save(evento);

            // Montar texto da notificação automaticamente
            String notificationText = "Novo evento: " + title + "\n" +
                "Descrição: " + text + "\n" +
                "Local: " + local + "\n" +
                "Data Inicial: " + dataInicio.replace('T', ' ') + "\n" +
                "Data Final: " + dataFim.replace('T', ' ');

            // Notificar usuários por WhatsApp
            try {
                java.util.List<Usuario> usuariosWhatsapp = usuarioRepository.findByNotificaWhatsappTrue();
                for (Usuario usuario : usuariosWhatsapp) {
                    if (usuario.getTelefone() != null && !usuario.getTelefone().isEmpty()) {
                        String numero = usuario.getTelefone();
                        if (!numero.startsWith("+")) {
                            numero = "+55" + numero.replaceAll("[^0-9]", "");
                        }
                        whatsAppService.enviarMensagem(numero, notificationText);
                    }
                }
            } catch (Exception e) {
                // Log ou tratamento de erro
            }
            // Notificar usuários por e-mail
            try {
                java.util.List<Usuario> usuariosEmail = usuarioRepository.findByNotificaEmailTrue();
                for (Usuario usuario : usuariosEmail) {
                    if (usuario.getEmail() != null && !usuario.getEmail().isEmpty()) {
                        SimpleMailMessage mail = new SimpleMailMessage();
                        mail.setTo(usuario.getEmail());
                        mail.setSubject("Novo evento: " + title);
                        mail.setText(notificationText);
                        mailSender.send(mail);
                    }
                }
            } catch (Exception e) {
                // Log ou tratamento de erro
            }

            return ResponseEntity.ok("Evento postado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao postar evento: " + e.getMessage());
        }
    }
}
