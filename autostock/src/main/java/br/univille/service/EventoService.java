package br.univille.service;

import br.univille.model.Evento;
import br.univille.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:5173")
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {
        return eventoRepository.findById(id).map(evento -> {
            evento.setNome(eventoAtualizado.getNome());
            evento.setLocal(eventoAtualizado.getLocal());
            // evento.setData(eventoAtualizado.getData());
            evento.setDescricao(eventoAtualizado.getDescricao());
            // evento.setHora(eventoAtualizado.getHora());
            evento.setAtivo(eventoAtualizado.getAtivo());
            return eventoRepository.save(evento);
        }).orElseGet(() -> {
            eventoAtualizado.setId(id);
            return eventoRepository.save(eventoAtualizado);
        });
    }
}
