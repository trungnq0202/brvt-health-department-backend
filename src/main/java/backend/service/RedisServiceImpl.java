package backend.service;

import backend.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

    private final String KEY = "PATIENT";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Patient> hashOperations;

    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Patient savePatient(Patient patient) {
        hashOperations.put(KEY, String.valueOf(patient.getID()), patient);
        return patient;
    }

    @Override
    public Map<String, Patient> getAllPatients() {
        return hashOperations.entries(KEY);
    }

    @Override
    public Patient getPatient(long id) {
        return hashOperations.get(KEY, String.valueOf(id));
    }

    @Override
    public long deletePatient(long id) {
        hashOperations.delete(KEY, String.valueOf(id));
        return id;
    }
}
