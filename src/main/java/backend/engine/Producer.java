package backend.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String SAVE_DOCTOR = "save_doctor";
    private static final String SAVE_PATIENT = "save_patient";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public <T> void sendSaveDoctorMessage(T t) {
        logger.info(String.format("#### -> Producing message -> %s", t));
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(t);

        this.kafkaTemplate.send(SAVE_DOCTOR, json);
    }

    public <T> void sendSavePatientMessage(T t) {
        logger.info(String.format("#### -> Producing message -> %s", t));
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(t);

        this.kafkaTemplate.send(SAVE_PATIENT, json);
    }
}

