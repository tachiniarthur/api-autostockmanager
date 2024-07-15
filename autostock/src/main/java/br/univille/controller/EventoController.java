package br.univille.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.model.Evento;
import br.univille.repository.EventoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    //Listar todos os eventos
    @GetMapping
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    //Buscar um evento pelo ID
    @GetMapping("/{id}")
    public Evento buscarPorId(@PathVariable Long id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.orElse(null);
    }

    //Criar um novo evento
    @PostMapping
    public Evento criarEvento(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }

    //Atualizar um evento existente
    @PutMapping("/{id}")
    public Evento atualizarEvento(@PathVariable Long id, @RequestBody Evento eventoAtualizado) {
        return eventoRepository.findById(id)
            .map(evento -> {
                evento.setNome(eventoAtualizado.getNome());
                evento.setLocal(eventoAtualizado.getLocal());
                evento.setData(eventoAtualizado.getData());
                evento.setDescricao(eventoAtualizado.getDescricao());
                evento.setHora(eventoAtualizado.getHora());
                evento.setAtivo(eventoAtualizado.getAtivo());
                return eventoRepository.save(evento);
            })
            .orElseGet(() -> {
                eventoAtualizado.setId(id);
                return eventoRepository.save(eventoAtualizado);
            });
    }

    // Atualizar status evento existente
    @PutMapping("/status/{id}")
    public Evento mudarStatusEvento(@PathVariable Long id, @RequestBody Evento eventoAtualizado) {
        return eventoRepository.findById(id)
            .map(evento -> {
                evento.setAtivo(eventoAtualizado.getAtivo());
                return eventoRepository.save(evento);
            })
            .orElseGet(() -> {
                eventoAtualizado.setId(id);
                return eventoRepository.save(eventoAtualizado);
            });
    }

    // Deletar um evento
    @DeleteMapping("/{id}")
    public void deletarEvento(@PathVariable Long id) {
        eventoRepository.deleteById(id);
    }
}
