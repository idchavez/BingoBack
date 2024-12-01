package com.bingoback.persistence.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bombo {
    private List<Integer> numerosDisponibles;

    public Bombo() {
        numerosDisponibles = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numerosDisponibles.add(i);
        }
        Collections.shuffle(numerosDisponibles);
    }

    public int sacarNumero() {
        if (!numerosDisponibles.isEmpty()) {
            return numerosDisponibles.remove(0);
        } else {
            throw new IllegalStateException("No hay mas numeros disponibles");
        }
    }
}
