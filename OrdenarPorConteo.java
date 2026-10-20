import java.util.HashMap;

public class OrdenarPorConteo {
    public static void main(String[] args) {
        HashMap<Integer, Valor> palabras = new HashMap<>();
        System.out.println("Ingrese un texto:");
        String texto = System.console().readLine();
        cargarPalabras(texto, palabras);
        for (Valor valor : palabras.values()) {
            System.out.println(valor.nombre + ": " + valor.cantidad);
        }
    }

    public static void cargarPalabras(String texto, HashMap<Integer, Valor> palabras) {
        String[] palabrasArray = texto.split("[\\s,\\.!?;:]");
        for (String palabra : palabrasArray) {
            if (!palabra.equals("")) {
                int clave = palabra.hashCode();
                if (!palabras.containsKey(clave)) {
                    palabras.put(clave, new Valor(palabra, 1));
                } else {
                    Valor valorExistente = palabras.get(clave);
                    palabras.put(clave, new Valor(palabra, valorExistente.cantidad + 1));
                }
            }
        }
    }

    public static class Valor {
        String nombre;
        Integer cantidad;

        public Valor(String nombre, Integer cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }
    }
}