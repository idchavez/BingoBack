package com.bingoback.controller;

import com.bingoback.service.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/bingo")
@CrossOrigin(value = "http:localhost:4200")
public class JuegoController {
    @Autowired
    private JuegoService juegoService;

    private int[][] tarjeta;

    @PostMapping("/sala")
    public int[][] iniciarJuego() {
        tarjeta = juegoService.generarTarjeta();
        return tarjeta;
    }

    @GetMapping("/sorteo")
    public Map<String, Object> sortearNumero() {
        int numeroSorteado = juegoService.sortearNumero();
        tarjeta = juegoService.validarNumero(tarjeta, numeroSorteado);
        return Map.of(
                "numeroSorteado", numeroSorteado,
                "tarjeta", tarjeta
        );
    }

    @GetMapping("/tarjeta")
    public int[][] obtenerTarjeta() {
        return tarjeta;
    }
}
