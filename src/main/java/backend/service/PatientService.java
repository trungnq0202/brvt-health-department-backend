package backend.service;

import backend.model.Patient;

import java.util.List;

public interface PatientService {
    public Patient savePatient(Patient patient);
    public List<Patient> getAllPatients();
    public Patient getPatient(long id);
    public String validate(String name, String password);
    public long deletePatient(long id);
}
