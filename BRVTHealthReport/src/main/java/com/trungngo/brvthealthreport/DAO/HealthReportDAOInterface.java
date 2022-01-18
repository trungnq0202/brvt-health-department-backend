package com.trungngo.brvthealthreport.DAO;

import com.trungngo.brvthealthreport.model.HealthReport;

import java.util.List;

public interface HealthReportDAOInterface {
    List<HealthReport> findAll();
    List<HealthReport> findAllByPatientId(Integer id);
    List<HealthReport> findByStatus(String status);
    HealthReport save(HealthReport healthReport);
    HealthReport update(HealthReport healthReport);
    HealthReport delete(Integer id);
    HealthReport findById(Integer id);
}
