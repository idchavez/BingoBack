package com.bingoback.auth.service;

import com.bingoback.auth.persistence.entity.Sesion;
import com.bingoback.auth.persistence.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    public List<Sesion> getAllSessions() {
        return sesionRepository.findAll();
    }

    public void addSession(Sesion sesion) {
        sesionRepository.save(sesion);
    }

    public void removeSession(Long id) {
        sesionRepository.deleteById(id);
    }
}
