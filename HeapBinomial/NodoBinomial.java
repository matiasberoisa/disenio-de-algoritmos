package HeapBinomial;

public class NodoBinomial {
    private Comparable elemento;
    private int grado;
    private NodoBinomial padre;
    private NodoBinomial hijo;
    private NodoBinomial hermano;

    public NodoBinomial(Comparable elemento) {
        this.elemento = elemento;
        this.grado = 0;
        this.padre = null;
        this.hijo = null;
        this.hermano = null;
    }

    public Comparable getelemento() {
        return elemento;
    }

    public void setelemento(Comparable elemento) {
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

    public int compareTo(Comparable otroElemento) {
        return this.elemento.compareTo(otroElemento);
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