package backend.controller;

import backend.engine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    //.\bin\windows\kafka-server-start.bat .\config\server.properties
    @PostMapping(value = "/doctor/save")
    public <T> T sendSaveDoctorMessageToKafkaTopic(@RequestBody T t) {
        this.producer.sendSaveDoctorMessage(t);
        return t;
    }

    @PostMapping(value = "/patient/save")
    public <T> T sendSavePatientMessageToKafkaTopic(@RequestBody T t) {
        this.producer.sendSavePatientMessage(t);
        return t;
    }
}
