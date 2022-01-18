package com.trungngo.brvthealthreport.service;

import com.trungngo.brvthealthreport.DAO.HealthReportDAOInterface;
import com.trungngo.brvthealthreport.model.HealthReport;
import com.trungngo.brvthealthreport.repository.HealthReportRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class HealthReportServiceImpl implements HealthReportServiceInterface{

    private final HealthReportDAOInterface healthReportDAO;
    private final HealthReportRedisRepository healthReportRedis;
    private final List<String> healthReportStatus = Arrays.asList("positive", "negative", "na"); //Save to redis cache for reports which status are positive

    @Autowired
    public HealthReportServiceImpl(@Qualifier("healthReportDAOImpl") HealthReportDAOInterface healthReportDAO, HealthReportRedisRepository healthReportRedis) {
        this.healthReportDAO = healthReportDAO;
        this.healthReportRedis = healthReportRedis;
    }

    @Override
    @Transactional
    public HealthReport addHealthReport(HealthReport healthReport) {

        if (healthReport.getStatus() != null) {
            String status = healthReport.getStatus().trim().toLowerCase();
            if (status.equals("")) {
                status = "na";
            }
            if (healthReportStatus.contains(status)) {
                healthReport.setStatus(status);
                HealthReport newHealthReport = healthReportDAO.save(healthReport);
                if (newHealthReport.getStatus().equals("postive")) {
                    healthReportRedis.add(newHealthReport);
                }
                return newHealthReport;
            }
        }
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
        HealthReport curHealthReport = healthReportRedis.getById(id);
        healthReportRedis.delete(id);
        if (curHealthReport == null) {
            curHealthReport = healthReportDAO.findById(id);
        }
        if (curHealthReport != null) {
            healthReportDAO.delete(id);
            return "Deleted Health Report id " + id;
        }
        else return "No Health Report found to delete.";
    }

    @Override
    @Transactional
    public List<?> findHealthReportsByStatus(String status) {
        if (status.equals("positive")){
            List<Object> redisHealthReports = healthReportRedis.getAll();
            List<HealthReport> healthReports = healthReportDAO.findByStatus(status);
            redisHealthReports.addAll(healthReports);
            return redisHealthReports;
        }
        else return healthReportDAO.findByStatus(status);
    }

    @Override
    @Transactional
    public HealthReport updateHealthReport(HealthReport healthReport) {
        int id = healthReport.getId();
        HealthReport foundHealthReport = healthReportRedis.getById(id);
        if (foundHealthReport == null) {
            foundHealthReport = healthReportDAO.findById(id);
        }
        if (foundHealthReport != null) {
            if (foundHealthReport.getStatus().equals("positive")){
                healthReportRedis.add(foundHealthReport);
            }
            return healthReportDAO.save(foundHealthReport);
        } else {
            return null;
        }
    }



}
