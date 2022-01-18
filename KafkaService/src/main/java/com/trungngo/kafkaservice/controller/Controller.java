package com.trungngo.kafkaservice.controller;

import com.trungngo.kafkaservice.engine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class Controller {
    private final Producer producer;

    @Autowired
    Controller(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/doctor/save")
    public <T> T sendSaveMessageToKafkaTopic(@RequestBody T t) {
        this.producer.sendSaveMessage(t);
        return t;
    }

    @PostMapping(value = "/doctor/put")
    public <T> T sendPutMessageToKafkaTopic(@RequestBody T t) {
        this.producer.sendPutMessage(t);
        return t;
    }


}
