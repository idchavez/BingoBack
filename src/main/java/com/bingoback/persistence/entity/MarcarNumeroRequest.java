package com.bingoback.persistence.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MarcarNumeroRequest {
    private int[][] matriz;
    private int numeroSorteado;
    private int i;
    private int j;
}
