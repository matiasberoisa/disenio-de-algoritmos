package HeapBinomial;

public class NodoBinomial {
    private int elemento;
    private int grado;
    private NodoBinomial padre;
    private NodoBinomial hijo;
    private NodoBinomial hermano;

    public NodoBinomial(int elemento) {
        this.elemento = elemento;
        this.grado = 0;
        this.padre = null;
        this.hijo = null;
        this.hermano = null;
    }

    public int getelemento() {
        return elemento;
    }

    public void setelemento(int elemento) {
        this.elemento = elemento;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public NodoBinomial getPadre() {
        return padre;
    }

    public void setPadre(NodoBinomial padre) {
        this.padre = padre;
    }

    public NodoBinomial getHijo() {
        return hijo;
    }

    public void setHijo(NodoBinomial hijo) {
        this.hijo = hijo;
    }

    public NodoBinomial getHermano() {
        return hermano;
    }

    public void setHermano(NodoBinomial hermano) {
        this.hermano = hermano;
    }

    public int compareTo(NodoBinomial otroNodo) {
        return Integer.compare(this.elemento, otroNodo.elemento);
    }

    public String toString() {
        return "NodoBinomial{" +
                "elemento =" + elemento +
                ", grado =" + grado +
                ", padre = " + padre +
                ", hijo = " + hijo +
                ", hermano = " + hermano +
                '}';
    }
}