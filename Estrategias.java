import java.util.ArrayList;
import java.util.List;

public class Estrategias {

    public static void main(String[] args) {
        int n = buscarVocalesSeguidas(new String[] { "A", "B", "C", "D", "E" });
        System.out.println("Número de permutaciones con vocales seguidas: " + n);
    }

    public static int buscarVocalesSeguidas(String[] letras) {
        int contador = 0;
        List<String> cadenas = new ArrayList<>();
        boolean[] letraUsada = new boolean[letras.length];
        String[] cadena = new String[letras.length];
        permutacionesIterativo(letras, cadena, cadenas);
        for (String palabra : cadenas) {
            if (palabra.contains("AE") || palabra.contains("EA") ||
                    palabra.contains("AI") || palabra.contains("IA") ||
                    palabra.contains("AO") || palabra.contains("OA") ||
                    palabra.contains("AU") || palabra.contains("UA") ||
                    palabra.contains("EI") || palabra.contains("IE") ||
                    palabra.contains("EO") || palabra.contains("OE") ||
                    palabra.contains("EU") || palabra.contains("UE") ||
                    palabra.contains("IO") || palabra.contains("OI") ||
                    palabra.contains("IU") || palabra.contains("UI") ||
                    palabra.contains("OU") || palabra.contains("UO")) {
                contador++;
            }
        }
        return contador;
    }

    private static void permutacionesIterativo(String[] letras,
            String[] cadena, List<String> cadenas) {
        int[] indices = new int[letras.length];
        int i, limite;
        boolean fin = false;
        while (!fin) {
            boolean[] letraUsada = new boolean[letras.length];
            boolean valida = true;
            i = 0;
            while (i < letras.length && valida) {
                if (letraUsada[indices[i]]) {
                    valida = false;
                } else {
                    letraUsada[indices[i]] = true;
                }
                i++;
            }
            if (valida) {
                for (int j = 0; j < letras.length; j++) {
                    cadena[j] = letras[indices[j]];
                }
                System.out.println("Permutación: " + String.join("", cadena));
                cadenas.add(String.join("", cadena));
            }
            limite = letras.length - 1;
            while (limite >= 0) {
                indices[limite]++;
                if (indices[limite] < letras.length) {
                    break;
                }
                indices[limite] = 0;
                limite--;
            }
            if (limite < 0) {
                fin = true;
            }
        }
    }

    private static void permutacionesRecursivo(String[] letras, boolean[] letraUsada,
            String[] cadena, int nivel, List<String> cadenas) {
        if (nivel == letras.length) {
            cadenas.add(String.join("", cadena));
        } else {
            for (int i = 0; i < letras.length; i++) {
                if (!letraUsada[i]) {
                    letraUsada[i] = true;
                    cadena[nivel] = letras[i];
                    permutacionesRecursivo(letras, letraUsada, cadena, nivel + 1, cadenas);
                    letraUsada[i] = false;
                }
            }
        }
    }
}
