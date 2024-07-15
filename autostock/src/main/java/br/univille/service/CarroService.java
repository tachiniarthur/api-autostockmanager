package br.univille.service;

import br.univille.model.Carros;
import br.univille.repository.CarrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarrosRepository carrosRepository;

    public List<Carros> listarTodos() {
        return carrosRepository.findAll();
    }

    public Optional<Carros> buscarPorId(Long id) {
        return carrosRepository.findById(id);
    }

    public Carros salvarcarros(Carros carros) {
        return carrosRepository.save(carros);
    }

    public void deletarcarros(Long id) {
        carrosRepository.deleteById(id);
    }

    public Carros atualizarcarros(Long id, Carros carrosAtualizado) {
        return carrosRepository.findById(id).map(carros -> {
            carros.setmodelo(carrosAtualizado.getmodelo());
            carros.setano(carrosAtualizado.getano());
            carros.setcor(carrosAtualizado.getcor());
            carros.setquilometragem(carrosAtualizado.getquilometragem());
            carros.setvalor(carrosAtualizado.getvalor());
            carros.setAtivo(carrosAtualizado.getAtivo());
            return carrosRepository.save(carros);
        }).orElseGet(() -> {
            carrosAtualizado.setId(id);
            return carrosRepository.save(carrosAtualizado);
        });
    }
}
