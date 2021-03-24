package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Fauna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FaunaRepository extends JpaRepository<Fauna, Long> {

    @Query("SELECT f FROM Fauna f WHERE f.camping.id_camping = ?1")
    Iterable<Fauna> findFaunasByCamping(Long id);
}
