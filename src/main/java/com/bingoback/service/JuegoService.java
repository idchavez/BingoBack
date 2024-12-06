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

    public int[][] validarNumero(int[][] matriz, int numero, int i, int j) {
        if (matriz[i][j] == numero) {
            matriz[i][j] = 0;
        } else {
            throw new IllegalArgumentException("El numero no coincide con el sorteado");
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

    public boolean esGanador(int[][] tarjeta) {
        boolean tableroLleno = true;
        for (int[] fila: tarjeta) {
            for (int num: fila) {
                if(num != 0) {
                    tableroLleno = false;
                    break;
                }
            }
            if(!tableroLleno) break;
        }

        boolean esquinasLlenas =
                tarjeta[0][0] == 0 && tarjeta[0][tarjeta[0].length - 1] == 0 &&
                tarjeta[tarjeta.length - 1][0] == 0 && tarjeta[tarjeta.length -1][tarjeta[0].length -1] == 0;

        boolean diagonal = true;
        for (int i = 0; i < tarjeta.length; i++) {
            if (tarjeta[i][tarjeta.length - 1 - i] != 0) {
                diagonal = false;
                break;
            }
        }

        boolean filaLlena = true;
        for (int j = 0; j < tarjeta[3].length; j++) {
            if (tarjeta[3][j] != 0) {
                filaLlena = false;
                break;
            }
        }

        boolean columnaLlena = true;
        for (int i = 0; i < tarjeta.length; i++) {
            if (tarjeta[i][3] != 0) {
                columnaLlena = false;
                break;
            }
        }

        return tableroLleno || esquinasLlenas || filaLlena || columnaLlena;
    }

    public void reiniciarBombo(){
        this.numerosSorteados.clear();
    }

}
