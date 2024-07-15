package br.univille.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.model.Usuario;
import br.univille.repository.UsuarioRepository;
import br.univille.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    //Listar todos os usuários
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    //Buscar um usuário pelo ID
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    //Buscar um usuário pelo Matricula
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Usuario> buscarPorMatricula(@PathVariable String matricula) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorMatricula(matricula);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Criar um novo usuário
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //Atualizar um usuário existente
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setMatricula(usuarioAtualizado.getMatricula());
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setEmail(usuarioAtualizado.getEmail());
                usuario.setAtivo(usuarioAtualizado.getAtivo());
                return usuarioRepository.save(usuario);
            })
            .orElseGet(() -> {
                usuarioAtualizado.setId(id);
                return usuarioRepository.save(usuarioAtualizado);
            });
    }

    // Atualizar senha usuário existente
    @PutMapping("/senha/{id}")
    public Usuario mudarSenhaUsuario(@PathVariable Long id, @RequestBody String senha) {
        System.out.println(senha);
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setSenha(senha);
                return usuarioRepository.save(usuario);
            })
            .orElse(null);
    }

    // Atualizar status usuário existente
    @PutMapping("/status/{id}")
    public Usuario mudarStatusUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setAtivo(usuarioAtualizado.getAtivo());
                return usuarioRepository.save(usuario);
            })
            .orElseGet(() -> {
                usuarioAtualizado.setId(id);
                return usuarioRepository.save(usuarioAtualizado);
            });
    }

    // Deletar um usuário
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
