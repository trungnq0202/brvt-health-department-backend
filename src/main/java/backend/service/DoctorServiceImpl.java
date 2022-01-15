package backend.service;

import backend.model.Doctor;
import backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor){
        this.doctorRepository.save(doctor);
        return doctor;
    }

    public Doctor getDoctor(long id){
        Doctor doctor = null;
        try {
            doctor = this.doctorRepository.findById(id)
                    .orElseThrow(() -> new Exception("Doctor not found for this id :: " + id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor;

    }

    public List<Doctor> getAllDoctors(){
        return this.doctorRepository.findAll();
    }

    public long deleteDoctor(long id) {
        Doctor doctor = getDoctor(id);
        this.doctorRepository.delete(doctor);
        return doctor.getID();
    }
}
