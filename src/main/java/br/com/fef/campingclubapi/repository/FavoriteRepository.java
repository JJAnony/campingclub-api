package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
