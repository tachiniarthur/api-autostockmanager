package br.univille.service;

import br.univille.model.Evento;
import br.univille.repository.EventoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EventoServiceTest {

    @Autowired
    private EventoService eventoService;

    @MockBean
    private EventoRepository eventoRepository;

    @Test
    public void testSalvarEvento() {
        Evento evento = new Evento();
        evento.setNome("Test User");
        when(eventoRepository.save(any(Evento.class))).thenReturn(evento);

        Evento savedEvento = eventoService.salvarEvento(evento);
        assertEquals("Test User", savedEvento.getNome());
    }

    @Test
    public void testBuscarPorId() {
        Evento evento = new Evento();
        evento.setId(1L);
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));

        Optional<Evento> foundEvento = eventoService.buscarPorId(1L);
        assertEquals(true, foundEvento.isPresent());
        assertEquals(1L, foundEvento.get().getId());
    }
}
