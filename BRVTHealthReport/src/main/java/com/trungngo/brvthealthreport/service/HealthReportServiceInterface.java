package com.trungngo.brvthealthreport.service;

import com.trungngo.brvthealthreport.model.HealthReport;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface HealthReportServiceInterface {

    HealthReport addHealthReport(HealthReport healthReport);

    HealthReport findHealthReportById(Integer id);

    List<HealthReport> findAllHealthReportByPatientId(int patientId);

    String deleteHealthReportById(Integer id);

    HealthReport updateHealthReport(HealthReport healthReport);


}
