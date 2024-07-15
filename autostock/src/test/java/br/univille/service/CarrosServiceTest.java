package br.univille.service;

import br.univille.model.Carros;
import br.univille.repository.CarrosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CarrosServiceTest {

    @Autowired
    private CarroService carroservice;

    @MockBean
    private CarrosRepository carrosRepository;

    @Test
    public void testSalvarcarros() {
        Carros carros = new Carros();
        carros.setmodelo("c3");
        when(carrosRepository.save(any(Carros.class))).thenReturn(carros);

        Carros savedcarros = carroservice.salvarcarros(carros);
        assertEquals("c3", savedcarros.getmodelo());
    }

    @Test
    public void testBuscarPorId() {
        Carros carros = new Carros();
        carros.setId(1L);
        when(carrosRepository.findById(1L)).thenReturn(Optional.of(carros));

        Optional<Carros> foundcarros = carroservice.buscarPorId(1L);
        assertEquals(true, foundcarros.isPresent());
        assertEquals(1L, foundcarros.get().getId());
    }
}

