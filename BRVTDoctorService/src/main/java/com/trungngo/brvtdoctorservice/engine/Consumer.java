package com.trungngo.brvtdoctorservice.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trungngo.brvtdoctorservice.model.Doctor;
import com.trungngo.brvtdoctorservice.service.DoctorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private DoctorServiceImpl doctorService;

    @KafkaListener(topics = "save_doctor", groupId = "group_id")
    public void save(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();
        Doctor doctor = gson.fromJson(json, Doctor.class);
        logger.info(String.format("#### -> Consumed message -> %s", doctor));

        //save item into database
        doctorService.addDoctor(doctor);
        logger.info(String.format("#### -> Finished saving item"));
    }

    @KafkaListener(topics = "put_doctor", groupId = "group_id")
    public void put(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Gson gson = gsonBuilder.create();
        Doctor doctor = gson.fromJson(json, Doctor.class);
        logger.info(String.format("#### -> Consumed message -> %s", doctor));

        //put item into database
        doctorService.updateDoctor(doctor);
        logger.info(String.format("#### -> Finished updating item"));
    }

}
