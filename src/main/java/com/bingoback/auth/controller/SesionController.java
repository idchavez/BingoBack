package com.bingoback.auth.controller;

import com.bingoback.auth.persistence.entity.Sesion;
import com.bingoback.auth.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bingo/")
@CrossOrigin(value = "http://localhost:4200")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @GetMapping("/sesiones")
    public List<Sesion> obtenerSesiones() {
        return sesionService.getAllSessions();
    }

    @PostMapping("/sesiones")
    public void addSesion(@RequestBody Sesion sesion) {
        sesionService.addSession(sesion);
    }

    @DeleteMapping("/sesiones/{id}")
    public void removeSession(@PathVariable Long id) {
        sesionService.removeSession(id);
    }
}
