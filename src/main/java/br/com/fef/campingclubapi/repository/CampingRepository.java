package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Camping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampingRepository extends JpaRepository<Camping, Long> {


}
