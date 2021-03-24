package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Flora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FloraRepository extends JpaRepository<Flora, Long> {

    @Query("SELECT f FROM Flora f WHERE f.camping.id_camping = ?1")
    Iterable<Flora> findFlorasByCamping(Long id);

}
