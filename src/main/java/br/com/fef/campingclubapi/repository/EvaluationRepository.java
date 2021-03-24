package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query(value = "SELECT e FROM Evaluation e WHERE e.user.id_user = ?1 AND e.camping.id_camping = ?2")
    Evaluation findEvaluationByUserAndCamping(Long id_user, Long id_camping);
}
