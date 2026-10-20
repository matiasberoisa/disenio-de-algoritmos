
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matias.beroisa
 */
public class Nodo {

    private Object elem;
    private Nodo enlace;

    public Nodo(Object el, Nodo en) {
        elem = el;
        enlace = en;
    }

    public Object getElem() {
        return this.elem;
    }

    public void setElem(Object el) {
        elem = el;
    }

    public Nodo getEnlace() {
        return this.enlace;
    }

    public void setEnlace(Nodo en) {
        enlace = en;
    }
}
