package com.trungngo.brvthealthreport.service;

import com.trungngo.brvthealthreport.DAO.HealthReportDAOInterface;
import com.trungngo.brvthealthreport.model.HealthReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
public class HealthReportServiceImpl implements HealthReportServiceInterface{

    private final HealthReportDAOInterface healthReportDAO;

    @Autowired
    public HealthReportServiceImpl(@Qualifier("healthReportDAOImpl") HealthReportDAOInterface healthReportDAO) {
        this.healthReportDAO = healthReportDAO;
    }

    @Override
    @Transactional
    public HealthReport addHealthReport(HealthReport healthReport) {
        return healthReportDAO.save(healthReport);
    }

    @Override
    @Transactional
    public HealthReport findHealthReportById(Integer id) {
        return healthReportDAO.findById(id);
    }

    @Override
    @Transactional
    public List<HealthReport> findAllHealthReportByPatientId(int patientId) {
        return null;
    }

    @Override
    @Transactional
    public String deleteHealthReportById(Integer id) {
        HealthReport healthReport = findHealthReportById(id);
        if(healthReport != null) {
            healthReportDAO.delete(id);
            return "Health Report deleted.";
        }
        else return "No Health Report found to delete.";
    }

    @Override
    @Transactional
    public HealthReport updateHealthReport(HealthReport healthReport) {
        return healthReportDAO.update(healthReport);
    }



}
