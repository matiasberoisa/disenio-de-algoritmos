public class Gale {
    private int[] hombres;
    private int[][] preferencias_Hombres;
    private int[][] preferencias_Mujeres;
    private int[] parejas;
    private int[] mujeres;
    private int[][] prohibidos;

    public Gale(int[] hombres, int[] mujeres, int[][] preferencias_Hombres, int[][] preferencias_Mujeres,
            int[][] prohibidos) {
        this.hombres = hombres;
        this.mujeres = mujeres;
        this.preferencias_Hombres = preferencias_Hombres;
        this.preferencias_Mujeres = preferencias_Mujeres;
        this.parejas = new int[hombres.length];
        for (int i = 0; i < parejas.length; i++) {
            parejas[i] = -1; // Inicialmente, todos los hombres están solteros
        }
        this.prohibidos = prohibidos;
    }

    public void emparejar() {
        boolean[] hombresSolteros = new boolean[hombres.length];
        int hombreActual = 0, pos, posLista;
        for (int i = 0; i < hombresSolteros.length; i++) {
            hombresSolteros[i] = true; // Todos los hombres están solteros al inicio
        }
        while (hayHombreSoltero(hombresSolteros) && hombreActual < hombres.length) {
            Lista pref_H = new Lista();
            pos = 0;
            posLista = 1;
            while (pos < preferencias_Hombres[hombreActual].length) {// creo la lista filtrada
                if (prohibidos[hombreActual][pos] != 1) {
                    pref_H.insertar(preferencias_Hombres[hombreActual][pos], posLista);
                    posLista++;
                }
                pos++;
            }
            System.out.println(pref_H.toString());
            if (hombresSolteros[hombreActual]) {
                int mujerActual = 0;
                while (mujerActual < mujeres.length) {
                    int mujerPreferida = preferencias_Hombres[hombreActual][mujerActual];
                    if (parejas[hombreActual] == -1 && prohibidos[hombreActual][mujerActual] != 1) { // La mujer
                        // está
                        // soltera y no
                        // está
                        // prohibida
                        // int pos = 0;
                        while (pos < parejas.length && parejas[pos] != mujerPreferida) {
                            pos++;
                        }
                        if (pos == parejas.length) {
                            // La mujer no está emparejada con ningún otro hombre
                            parejas[hombreActual] = mujerPreferida; // Emparejar al hombre con la mujer
                            hombresSolteros[hombreActual] = false; // El hombre ya no está soltero
                        }
                    }
                    mujerActual++;
                }
            }
            hombreActual++;
        }
    }

    private boolean hayHombreSoltero(boolean[] hombresSolteros) {
        boolean sigueSoltero = false;
        for (boolean soltero : hombresSolteros) {
            if (soltero) {
                sigueSoltero = true;
                break;
            }
        }
        return sigueSoltero;
    }

    public static void main(String[] args) {
        int[] hombres = { 1, 2, 3 };
        int[] mujeres = { 4, 5, 6 };
        int[][] preferencias_Hombres = { { 4, 5, 6 }, { 5, 6, 4 }, { 5, 4, 6 } }; // Preferencias de los
        // hombres
        int[][] preferencias_Mujeres = { { 3, 1, 2 }, { 1, 3, 2 }, { 2, 3, 1 } }; // Preferencias de las
                                                                                  // mujeres
        int[][] prohibidos = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 0, 1 } }; // La pareja (2, 6) está prohibida

        Gale galeShapley = new Gale(hombres, mujeres, preferencias_Hombres, preferencias_Mujeres, prohibidos);
        galeShapley.emparejar();

        System.out.println("Parejas formadas:");
        for (int i = 0; i < galeShapley.parejas.length; i++) {
            if (galeShapley.parejas[i] != -1) {
                System.out.println("Hombre " + galeShapley.hombres[i] + " está emparejado con Mujer "
                        + galeShapley.parejas[i]);
            } else {
                System.out.println("Hombre " + galeShapley.hombres[i] + " está soltero");
            }
        }
    }
}
