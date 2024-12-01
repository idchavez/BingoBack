package com.bingoback.persistence.entity;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "tarjetas")
public class Tarjeta {

    private final int filas = 5;
    private final int columnas = 5;
    private int[][] matriz;

    public Tarjeta(){
        this.matriz = new int[filas][columnas];
    }

}
