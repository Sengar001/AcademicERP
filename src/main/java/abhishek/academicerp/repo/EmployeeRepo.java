package abhishek.academicerp.repo;

import abhishek.academicerp.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Long> {
    Optional<Employees> findByEmail(String email);
}
