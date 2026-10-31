public class Estrategias {
    
    public static void main(String[] args) {
        int n = buscarVocalesSeguidas(new char[] { 'A', 'B', 'C', 'D', 'E' });
    }

    public static int buscarVocalesSeguidas(char[] letras) {
        int n = factorial(letras.length);
        String[] cadenas = new String[n];
        for (int i = 0; i < n; i++) {
            String cadena = "";
            for (int j = 0; j < letras.length; j++) {
                cadena += letras[j];
            }
            cadenas[i] = cadena;
        }
        for (int i = 0; i < n; i++) {
            System.out.println(cadenas[i]);
        }
        return 0;
    }

    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
