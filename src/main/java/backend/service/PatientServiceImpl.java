package backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.model.Patient;
import backend.repository.PatientRepository;

import java.util.List;

@Transactional
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient){
        this.patientRepository.save(patient);
        return patient;
    }

    public Patient getPatient(long id){
        Patient patient = null;
        try {
            patient = this.patientRepository.findById(id)
                    .orElseThrow(() -> new Exception("Patient not found for this id :: " + id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;

    }

    public List<Patient> getAllPatients(){
        return this.patientRepository.findAll();
    }

    public long deletePatient(long id) {
        Patient patient = getPatient(id);
        this.patientRepository.delete(patient);
        return patient.getID();
    }

    public String validate(String name, String password) {
        Patient patient =  this.patientRepository.findPatientByNameAndPassword(name, password);
        if (patient == null) {
            return "Wrong password";
        }
        else {
            return "OK";
        }
    }
}
