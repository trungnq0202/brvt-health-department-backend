package backend.engine;

import backend.model.Doctor;
import backend.model.Patient;
import backend.service.DoctorServiceImpl;
import backend.service.PatientServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private DoctorServiceImpl doctorService;

    @Autowired
    private PatientServiceImpl patientService;

    @KafkaListener(topics = "save_doctor", groupId = "group_id")
    public void saveDoctor(String json) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Doctor doctor = gson.fromJson(json, Doctor.class);
        logger.info(String.format("#### -> Consumed message -> %s", doctor));

        //save doctor into database
        doctorService.saveDoctor(doctor);
        logger.info(String.format("#### -> Finished saving doctor"));
    }

    @KafkaListener(topics = "save_patient", groupId = "group_id")
    public void savePatient(String json) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Patient patient = gson.fromJson(json, Patient.class);
        logger.info(String.format("#### -> Consumed message -> %s", patient));

        //save patient into database
        patientService.savePatient(patient);
        logger.info(String.format("#### -> Finished saving patient"));
    }
}
