package com.bingoback.auth.persistence.repository;

import com.bingoback.auth.persistence.entity.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {
/*
    private final List<Sesion> sessions = new ArrayList<>();

    public List<Sesion> findAll() {
        return new ArrayList<>(sessions);
    }

    public void addSesion(Sesion sesion) {
        sessions.add(sesion);
    }

    public void removeSesion(String sessionId) {
        sessions.removeIf(sesion -> sesion.getSessionId().equals(sessionId));
    }
*/
}
