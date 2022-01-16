package backend.service;

import backend.model.Patient;
import java.util.Map;

public interface RedisService {
    public Patient savePatient(Patient patient);
    public Map<String, Patient> getAllPatients();
    public Patient getPatient(long id);
    public long deletePatient(long id);
}
