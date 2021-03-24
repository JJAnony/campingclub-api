package br.com.fef.campingclubapi.repository;

import br.com.fef.campingclubapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
