package backend.service;

import backend.model.Doctor;

import java.util.List;

public interface DoctorService {
    public Doctor saveDoctor(Doctor doctor);

    public List<Doctor> getAllDoctors();
    public Doctor getDoctor(long id);

    public long deleteDoctor(long id);
}
