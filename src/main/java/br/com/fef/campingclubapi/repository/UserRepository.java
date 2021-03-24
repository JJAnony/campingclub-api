package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByEmployeeIsNotNullAndEmployeeOffice(String office, Pageable pageable);

    User findByUsername(String username);


}
