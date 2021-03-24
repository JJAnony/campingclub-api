package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantaRepository extends JpaRepository<Plant, Long> {
}
