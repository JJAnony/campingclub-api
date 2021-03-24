package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
