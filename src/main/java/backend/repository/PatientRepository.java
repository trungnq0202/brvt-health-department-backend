package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.model.Patient;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT e FROM Patient e WHERE e.name like ?1 and e.password like ?2")
    Patient findPatientByNameAndPassword(String name, String password);
}
