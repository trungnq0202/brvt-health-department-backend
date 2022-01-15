package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
