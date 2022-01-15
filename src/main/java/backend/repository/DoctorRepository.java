package backend.repository;

import backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT e FROM Doctor e WHERE e.name like ?1 and e.password like ?2")
    Doctor findDoctorByNameAndPassword(String name, String password);
}
