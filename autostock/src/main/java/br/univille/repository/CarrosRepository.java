package br.univille.repository;

import br.univille.model.Carros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrosRepository extends JpaRepository<Carros, Long> {
    
}
