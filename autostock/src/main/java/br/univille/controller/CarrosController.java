package br.univille.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.univille.model.Carros;
import br.univille.repository.CarrosRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/carros")
public class CarrosController {

    @Autowired
    private CarrosRepository carrosRepository;

    //Listar todos os carros
    @GetMapping
    public List<Carros> listarTodos() {
        return carrosRepository.findAll();
    }

    //Buscar um carros pelo ID
    @GetMapping("/{id}")
    public Carros buscarPorId(@PathVariable Long id) {
        Optional<Carros> carros = carrosRepository.findById(id);
        return carros.orElse(null);
    }

    //Criar um novo carros
    @PostMapping
    public Carros criarcarros(@RequestBody Carros carros) {
        return carrosRepository.save(carros);
    }

    //Atualizar um carros existente
    @PutMapping("/{id}")
    public Carros atualizarcarros(@PathVariable Long id, @RequestBody Carros carrosAtualizado) {
        return carrosRepository.findById(id)
            .map(carros -> {
                carros.setmodelo(carrosAtualizado.getmodelo());
                carros.setano(carrosAtualizado.getano());
                carros.setcor(carrosAtualizado.getcor());
                carros.setquilometragem(carrosAtualizado.getquilometragem());
                carros.setvalor(carrosAtualizado.getvalor());
                carros.setAtivo(carrosAtualizado.getAtivo());
                return carrosRepository.save(carros);
            })
            .orElseGet(() -> {
                carrosAtualizado.setId(id);
                return carrosRepository.save(carrosAtualizado);
            });
    }

    // Atualizar status usuário existente
    @PutMapping("/status/{id}")
    public Carros mudarStatusCarro(@PathVariable Long id, @RequestBody Carros carrosAtualizado) {
        return carrosRepository.findById(id)
            .map(carros -> {
                carros.setAtivo(carrosAtualizado.getAtivo());
                return carrosRepository.save(carros);
            })
            .orElseGet(() -> {
                carrosAtualizado.setId(id);
                return carrosRepository.save(carrosAtualizado);
            });
    }

    //DELETE: Deletar um carros
    @DeleteMapping("/{id}")
    public void deletarcarros(@PathVariable Long id) {
        carrosRepository.deleteById(id);
    }
}

