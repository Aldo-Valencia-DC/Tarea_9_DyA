package org.example;

import java.util.Stack;

public class Laberinto {
    static final char PARED = 'p';
    static final char CAMINO = 'c';
    static final char INICIO = 'E';
    static final char SALIDA = 's';

    static char[][] laberinto = {
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'p', 'p', 'c', 'c', 'c', 'c', 'c', 'p'},
            {'p', 'p', 'c', 'p', 'p', 'p', 'c', 'p'},
            {'p', 'p', 'c', 'c', 'c', 'p', 'c', 'p'},
            {'p', 'p', 'p', 'p', 'c', 'p', 'c', 's'},
            {'p', 'p', 'p', 'p', 'c', 'p', 'p', 'p'},
            {'p', 'p', 'c', 'c', 'c', 'c', 'p', 'p'},
            {'p', 'p', 'c', 'c', 'p', 'p', 'p', 'p'},
            {'p', 'p', 'E', 'p', 'p', 'p', 'p', 'p'}
    };

    static Stack<int[]> camino = new Stack<>();
    static int[][] direcciones = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) {
        int inicioX = 13, inicioY = 2;
        if (resolverLaberinto(inicioX, inicioY)) {
            System.out.println("Camino encontrado:");

            for (int i = camino.size() - 1; i >= 0; i--) {
                int[] paso = camino.get(i);
                System.out.println("(" + paso[0] + ", " + paso[1] + ")");
            }
        } else {
            System.out.println("No se encontró camino.");
        }
    }

    public static boolean resolverLaberinto(int x, int y) {
        if (!esValido(x, y) || laberinto[x][y] == PARED) {
            return false;
        }
        camino.push(new int[]{x, y});

        if (laberinto[x][y] == SALIDA) {
            return true;
        }

        laberinto[x][y] = PARED;
        for (int[] dir : direcciones) {
            int nuevoX = x + dir[0];
            int nuevoY = y + dir[1];
            if (resolverLaberinto(nuevoX, nuevoY)) {
                return true;
            }
        }
        laberinto[x][y] = CAMINO;
        camino.pop();
        return false;
    }
    public static boolean esValido(int x, int y) {
        return x >= 0 && y >= 0 && x < laberinto.length && y < laberinto[0].length;
    }
}
