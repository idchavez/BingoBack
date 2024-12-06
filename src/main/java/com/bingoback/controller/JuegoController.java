package com.bingoback.controller;

import com.bingoback.persistence.entity.MarcarNumeroRequest;
import com.bingoback.service.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/bingo")
@CrossOrigin(value = "http://localhost:4200")
public class JuegoController {
    @Autowired
    private JuegoService juegoService;

    private int[][] tarjeta;

    @PostMapping("/sala")
    public int[][] iniciarJuego() {
        return juegoService.generarTarjeta();
    }

    @GetMapping("/sorteo")
    public Map<String, Object> sortearNumero() {
        int numeroSorteado = juegoService.sortearNumero();
        return Map.of(
                "numeroSorteado", numeroSorteado
        );
    }

    @PostMapping("/marcarNumero")
    public ResponseEntity<int[][]> marcarNumero(@RequestBody MarcarNumeroRequest numRequest) {
        try {
            int[][] nuevaTarjeta = juegoService.validarNumero(
                    numRequest.getMatriz(),
                    numRequest.getNumeroSorteado(),
                    numRequest.getI(),
                    numRequest.getJ()
            );
            return ResponseEntity.ok(nuevaTarjeta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/tarjeta")
    public int[][] obtenerTarjeta() {
        return tarjeta;
    }

    @PostMapping("/validar")
    public Map<String, Object> ganadorBingo(@RequestBody int[][] tarjetaUsuario) {
        boolean ganador = juegoService.esGanador(tarjetaUsuario);
        return Map.of(
                "ganador", ganador,
                "mensaje", ganador ? "¡**Ganaste**!" : "Aun no completas el bingo!"
        );
    }

    @PostMapping("/reiniciar-bombo")
    public ResponseEntity<String> reiniciarBombo() {
        juegoService.reiniciarBombo();
        return ResponseEntity.ok("El Bombo ha sido reiniciado");
    }
}
