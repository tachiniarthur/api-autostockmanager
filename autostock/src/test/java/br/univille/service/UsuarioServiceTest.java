package br.univille.service;

import br.univille.model.Usuario;
import br.univille.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testSalvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Test User");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario savedUsuario = usuarioService.salvarUsuario(usuario);
        assertEquals("Test User", savedUsuario.getNome());
    }

    @Test
    public void testBuscarPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> foundUsuario = usuarioService.buscarPorId(1L);
        assertEquals(true, foundUsuario.isPresent());
        assertEquals(1L, foundUsuario.get().getId());
    }
}
