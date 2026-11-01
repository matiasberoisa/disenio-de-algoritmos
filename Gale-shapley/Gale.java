
public class Gale {
    private int[] hombres;
    private int[][] pref_H;
    private int[][] pref_M;
    private int[] parejasHombres;
    private int[] parejasMujeres;
    private int[] mujeres;
    private Boolean[][] prohibidos;

    public Gale(int[] hombres, int[] mujeres, int[][] pref_H, int[][] pref_M,
            Boolean[][] prohibidos) {
        this.hombres = hombres;
        this.mujeres = mujeres;
        this.pref_H = pref_H;
        this.pref_M = pref_M;
        this.parejasHombres = new int[hombres.length];
        for (int i = 0; i < parejasHombres.length; i++) {
            parejasHombres[i] = -1; // Inicialmente, todos los hombres están solteros
        }
        this.parejasMujeres = new int[mujeres.length];
        for (int i = 0; i < parejasMujeres.length; i++) {
            parejasMujeres[i] = -1; // Inicialmente, todas las mujeres están solteras
        }
        this.prohibidos = prohibidos;
    }

    public void emparejar() {
        int homAct = 0;
        int[] sigProp = new int[hombres.length];
        for (int i = 0; i < hombres.length; i++) {
            sigProp[i] = 1;
        }
        Lista[] listas = new Lista[hombres.length];
        for (int i = 0; i < hombres.length; i++) {
            listas[i] = new Lista();
            for (int j = 0; j < pref_H[i].length; j++) {
                if (!prohibidos[i][j]) {
                    listas[i].insertar(pref_H[i][j], listas[i].longitud() + 1);
                }
            }
        }
        while (hombreSoltero(parejasHombres) != -1) {
            homAct = hombreSoltero(parejasHombres);
            while (parejasHombres[homAct] == -1
                    && sigProp[homAct] <= listas[homAct].longitud()) {
                int mujerPref = (int) listas[homAct].recuperar(sigProp[homAct]);
                sigProp[homAct]++;
                if (parejasMujeres[mujerPref] == -1) {
                    parejasMujeres[mujerPref] = homAct;
                    parejasHombres[homAct] = mujerPref;
                } else {
                    int mujerAsign = parejasMujeres[mujerPref];
                    if (pref_M[mujerPref][homAct] < pref_M[mujerPref][mujerAsign]) {
                        parejasMujeres[mujerPref] = homAct;
                        parejasHombres[homAct] = mujerPref;
                        parejasHombres[mujerAsign] = -1;
                    }
                }
            }
            if (parejasHombres[homAct] == -1) {
                parejasHombres[homAct] = -2;
            }
        }
    }

    private int hombreSoltero(int[] parejas) {
        for (int i = 0; i < parejas.length; i++) {
            if (parejas[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] hombres = { 0, 1, 2, 3, 4 };
        int[] mujeres = { 0, 1, 2, 3, 4 };
        int[][] pref_H = {
                { 0, 1, 2, 3, 4 }, { 1, 2, 0, 4, 3 }, { 1, 0, 2, 4, 3 },
                { 2, 3, 4, 0, 1 }, { 4, 3, 2, 1, 0 } };
        int[][] pref_M = {
                { 3, 1, 2, 0, 4 }, { 1, 3, 2, 4, 0 }, { 2, 3, 1, 4, 0 },
                { 0, 1, 2, 3, 4 }, { 4, 3, 2, 1, 0 } };
        Boolean[][] prohibidos = {
                { true, false, false, false, true }, { false, false, true, false, true },
                { false, false, true, true, false }, { false, true, false, true, false },
                { false, false, false, false, false } };
        Gale g = new Gale(hombres, mujeres, pref_H, pref_M, prohibidos);
        g.emparejar();
        // impresión
        for (int i = 0; i < g.parejasHombres.length; i++) {
            if (g.parejasHombres[i] >= 0)
                System.out.println("Hombre " + g.hombres[i] + " con Mujer " + g.mujeres[g.parejasHombres[i]]);
            else
                System.out.println("Hombre " + g.hombres[i] + " está soltero");
        }
    }
}
