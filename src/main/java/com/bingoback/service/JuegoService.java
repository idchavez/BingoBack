package com.bingoback.service;

import com.bingoback.persistence.entity.Bombo;
import com.bingoback.persistence.entity.Tarjeta;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JuegoService {
    private final Bombo bombo;
    private final Set<Integer> numerosSorteados;

    public JuegoService() {
        this.bombo = new Bombo();
        this.numerosSorteados = new HashSet<>();
    }

    public int[][] generarTarjeta() {
        Tarjeta tarjeta = new Tarjeta();
        int[][] matriz = tarjeta.getMatriz();
        Random random = new Random();

        int[][] rangos = {
                {1,15}, {16,30}, {31,45}, {46,60}, {61,75}
        };

        for (int col = 0; col < 5; col++) {
            Set<Integer> numerosColumna = new HashSet<>();
            while(numerosColumna.size() < 5) {
                int numero = random.nextInt(rangos[col][1] - rangos[col][0] + 1) + rangos[col][0];
                numerosColumna.add(numero);
            }

            int fila = 0;
            for (int numero : numerosColumna) {
                matriz[fila][col] = numero;
                fila++;
            }
        }
        matriz[2][2] = 0;
        tarjeta.setMatriz(matriz);
        return matriz;
    }

    public int sortearNumero() {
        int numero = bombo.sacarNumero();
        numerosSorteados.add(numero);
        return numero;
    }

    public int[][] validarNumero(int[][] matriz, int numero) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(matriz[i][j] == numero) {
                    matriz[i][j] = 0;
                }
            }
        }
        return matriz;
    }

    public void imprimirTarjeta(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int num : fila ) {
                if (num == 0) {
                    System.out.print("**\t");
                } else {
                    System.out.print(num + "\t");
                }
            }
            System.out.println();
        }
    }

}
