package com.trungngo.brvthealthreport.controller;

import com.trungngo.brvthealthreport.model.HealthReport;
import com.trungngo.brvthealthreport.service.HealthReportServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/health-report")
public class HealthReportController {

    private final HealthReportServiceInterface healthReportService;

    @Autowired
    public HealthReportController(HealthReportServiceInterface healthReportService) {
        this.healthReportService = healthReportService;
    }

    @PostMapping(path="/add")
    public ResponseEntity addHealthReport(@RequestBody HealthReport healthReport) {
        HealthReport healthReportAdded = healthReportService.addHealthReport(healthReport);
        if(healthReport == null) {
            return new ResponseEntity<>(
                    "Failed to add health report",
                    HttpStatus.BAD_REQUEST
            );
        }
        else {
            return new ResponseEntity<>(
                    healthReportAdded,
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(path="/{id}")
    public ResponseEntity getHealthReportById(@PathVariable Integer id) {
        HealthReport healthReport = healthReportService.findHealthReportById(id);
        if(healthReport == null) return new ResponseEntity<>("Health report not found.", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(healthReport, HttpStatus.OK);
    }

    @GetMapping(path="/findByStatus/{status}")
    public ResponseEntity getHealthReportsByStatus(@PathVariable String status) {
        return new ResponseEntity<>(
                healthReportService.findHealthReportsByStatus(status),
                HttpStatus.OK
        );
    }

    @PutMapping(path="")
    public ResponseEntity updateHealthReport(@RequestBody HealthReport healthReport) {
        HealthReport updateHealthReport = healthReportService.updateHealthReport(healthReport);
        if (healthReport == null) {
            return new ResponseEntity<>(
                    "Failed to update health report",
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity<>(
                    updateHealthReport,
                    HttpStatus.OK
            );
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteHealthReportById(@PathVariable Integer id){
        return healthReportService.deleteHealthReportById(id);
    }

//    @GetMapping(path="/findAll")
//    public ResponseEntity findAllHealthReports() {
//        return new ResponseEntity<>(
//                healthReportService.find(),
//                HttpStatus.OK
//        );
//    }



}
