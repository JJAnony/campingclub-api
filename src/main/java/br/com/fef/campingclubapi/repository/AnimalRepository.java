package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
