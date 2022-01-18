package com.trungngo.brvthealthreport.repository;

import com.trungngo.brvthealthreport.model.HealthReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class HealthReportRedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String KEY = "HEALTHREPORT";
    private static int maximumRecords = 10;


    @Autowired
    public HealthReportRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.expire(KEY, 30, TimeUnit.DAYS);
    }

    public HealthReport add(HealthReport healthReport) {
        if (redisTemplate.opsForHash().size(KEY) < maximumRecords) {
            redisTemplate.opsForHash().put(KEY, Integer.toString(healthReport.getId()), healthReport);
        }
        return healthReport;
    }

    public List<Object> addHealthReports(List<HealthReport> healthReports) {
        healthReports.forEach(this::add);
        return getAll();
    }

    public List<Object> getAll() {
        return redisTemplate.opsForHash().values(KEY);
    }

    public HealthReport getById(int id) {
        return (HealthReport) redisTemplate.opsForHash().get(KEY, Integer.toString(id));
    }

    public void delete(int id) {
        redisTemplate.opsForHash().delete(KEY, Integer.toString(id));
    }


}
