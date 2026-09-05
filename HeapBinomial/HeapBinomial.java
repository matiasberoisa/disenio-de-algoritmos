package HeapBinomial;

public class HeapBinomial {
    private NodoBinomial raiz;

    public HeapBinomial() {
        this.raiz = null;
    }

    public HeapBinomial(NodoBinomial raiz) {
        this.raiz = raiz;
    }

    public NodoBinomial getRaiz() {
        return raiz;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public void vaciar() {
        raiz = null;
    }

    public void insertar(int elemento) {
        NodoBinomial nuevoNodo = new NodoBinomial(elemento);
        HeapBinomial nuevoHeap = new HeapBinomial(nuevoNodo);
        this.raiz = unir(nuevoHeap);
    }

    public NodoBinomial unir(HeapBinomial nuevoNodo) {
        NodoBinomial nuevoNodoRaiz = fusionar(this, nuevoNodo);
        this.raiz = null;
        nuevoNodo.raiz = null;
        if (nuevoNodoRaiz == null) { // caso 1: la raíz del nuevo nodo es nula, lo que significa que no hay nodos en
                                     // el heap
            nuevoNodoRaiz = null;
        } else { // caso 2: la raíz del nuevo nodo no es nula, lo que significa que hay nodos en
                 // el heap
            NodoBinomial anterior = null;
            NodoBinomial actual = nuevoNodoRaiz;
            NodoBinomial siguiente = actual.getHermano();

            while (siguiente != null) {
                if (actual.getGrado() != siguiente.getGrado()
                        || (siguiente.getHermano() != null && siguiente.getHermano().getGrado() == actual.getGrado())) {
                    anterior = actual;
                    actual = siguiente;
                } else {
                    if (actual.compareTo(siguiente) <= 0) {
                        actual.setHermano(siguiente.getHermano());
                        enlazar(siguiente, actual);
                    } else {
                        if (anterior == null) {
                            nuevoNodoRaiz = siguiente;
                        } else {
                            anterior.setHermano(siguiente);
                        }
                        enlazar(actual, siguiente);
                        actual = siguiente;
                    }
                }
            }
        }

        return nuevoNodoRaiz;
    }

    private NodoBinomial fusionar(HeapBinomial heap1, HeapBinomial heap2) {
        NodoBinomial nuevaRaiz = null;
        if (heap1.getRaiz() == null) {
            nuevaRaiz = heap2.getRaiz();
        } else if (heap2 == null) {
            nuevaRaiz = heap1.getRaiz();
        } else {
            NodoBinomial nodoSiguiente1 = heap1.getRaiz();
            NodoBinomial nodoSiguiente2 = heap2.getRaiz();

            if (nodoSiguiente1.getGrado() <= nodoSiguiente2.getGrado()) { // Comparar los grados de los nodos y
                                                                          // establecer la nueva raíz como el nodo con
                                                                          // menor grado
                nuevaRaiz = nodoSiguiente1; // Establecer la nueva raíz como el nodo con menor grado
                nodoSiguiente1 = nodoSiguiente1.getHermano(); // enlazo el hermano del heap1 al nuevo heap
            } else {
                nuevaRaiz = nodoSiguiente2;// Establecer la nueva raíz como el nodo con menor grado
                nodoSiguiente2 = nodoSiguiente2.getHermano();// enlazo el hermano del heap2 al nuevo heap
            }

            NodoBinomial nodoActual = nuevaRaiz;
            while (nodoSiguiente1 != null && nodoSiguiente2 != null) { // enlazo el resto de los nodos del heap1 y heap2
                                                                       // al nuevo heap
                if (nodoSiguiente1.getGrado() <= nodoSiguiente2.getGrado()) {
                    nodoActual.setHermano(nodoSiguiente1);
                    nodoSiguiente1 = nodoSiguiente1.getHermano();
                } else {
                    nodoActual.setHermano(nodoSiguiente2);
                    nodoSiguiente2 = nodoSiguiente2.getHermano();
                }
                nodoActual = nodoActual.getHermano();
            }
            if (nodoSiguiente1 != null) {// Si aún quedan nodos en el heap1, enlazarlos al nuevo heap
                nodoActual.setHermano(nodoSiguiente1);
            } else {
                nodoActual.setHermano(nodoSiguiente2);
            }
        }
        return nuevaRaiz;
    }

    private void enlazar(NodoBinomial nodo1, NodoBinomial nodo2) {
        nodo2.setPadre(nodo1);
        nodo2.setHermano(nodo1.getHijo());
        nodo1.setHijo(nodo2);
        nodo1.setGrado(nodo1.getGrado() + 1);
    }

    public NodoBinomial buscarMinimo() {
        if (this.raiz == null) {
            return null;
        }
        NodoBinomial nodoMinimo = this.raiz;
        NodoBinomial nodoActual = this.raiz.getHermano();
        while (nodoActual != null) {
            if (nodoActual.compareTo(nodoMinimo) < 0) {
                nodoMinimo = nodoActual;
            }
            nodoActual = nodoActual.getHermano();
        }
        return nodoMinimo;
    }

    public String toString() {
        String cadena = "Heap Binomial: ";
        if (this.raiz != null) {
            NodoBinomial nodoActual = this.raiz;
            while (nodoActual != null) {
                cadena += nodoActual.getelemento() + " ";
                nodoActual = nodoActual.getHermano();
            }
        }
        return cadena;
    }

    public boolean eliminar(int elemento) {
        boolean exito = false;
        if (!this.esVacio()) {
            NodoBinomial nodoBuscado = buscarNodo(this.raiz, elemento);
            if (this.raiz == nodoBuscado) {// Si el nodo buscado es la raíz, se actualiza la raíz del heap
                if (this.raiz.getHermano() != null) {
                    this.raiz = nodoBuscado.getHermano();// Si hay hermanos, se actualiza la raíz del heap al hermano
                                                         // del
                                                         // nodo buscado
                } else {
                    this.vaciar();// Si no hay hermanos, se vacía el heap
                }

            } else { // Si el nodo buscado no es la raíz, se actualiza el hermano del padre del nodo
                     // buscado
                NodoBinomial nodoAnterior = nodoBuscado.getPadre();
                while (nodoAnterior.compareTo(nodoBuscado) != 0) {
                    nodoAnterior = nodoAnterior.getHermano();
                }
                eliminarAux(nodoAnterior, nodoBuscado);
            }
        }
        return exito;
    }

    private NodoBinomial buscarNodo(NodoBinomial nodoActual, int elemento) {
        NodoBinomial nodoEncontrado = null;
        if (nodoActual != null) {
            if (nodoActual.getelemento() == elemento) {
                nodoEncontrado = nodoActual;
            } else {
                NodoBinomial hijoEncontrado = buscarNodo(nodoActual.getHijo(), elemento);
                if (hijoEncontrado != null) {
                    nodoEncontrado = hijoEncontrado;
                } else {
                    nodoEncontrado = buscarNodo(nodoActual.getHermano(), elemento);
                }
            }
        }
        return nodoEncontrado;
    }

    private void eliminarAux(NodoBinomial nodoActual, NodoBinomial nuevoNodo) {
        if (nodoActual.getelemento() == nuevoNodo.getelemento()) {
            this.raiz.setHermano(nodoActual.getHermano());
        } else {
            nuevoNodo.setHermano(nodoActual.getHermano());
        }

        NodoBinomial nuevaRaiz = null;
        NodoBinomial hijoActual = nuevoNodo.getHijo();
        while (hijoActual != null) {
            NodoBinomial hijoSiguiente = hijoActual.getHermano();
            hijoActual.setHermano(nuevaRaiz);
            nuevaRaiz = hijoActual;
            hijoActual = hijoSiguiente;
        }
        HeapBinomial nuevoHeap = new HeapBinomial(nuevaRaiz);

        this.raiz = unir(nuevoHeap);
    }

    public void extraerMinimo() {
        if (!this.esVacio()) {
            NodoBinomial nodoMinimo = buscarMinimo();
            this.eliminarAux(this.raiz, nodoMinimo);
        }
    }

    public void disminuirClave(int elemento, int nuevoElem) {
        if (!this.esVacio()) {
            NodoBinomial nodoBuscado = buscarNodo(this.raiz, elemento);
            if (nodoBuscado != null) {
                nodoBuscado.setelemento(nuevoElem);
                while (nodoBuscado.getPadre() != null && nodoBuscado.compareTo(nodoBuscado.getPadre()) < 0) {
                    int temp = nodoBuscado.getelemento();
                    nodoBuscado.setelemento(nodoBuscado.getPadre().getelemento());
                    nodoBuscado.getPadre().setelemento(temp);
                    nodoBuscado = nodoBuscado.getPadre();
                }
            }
        }
    }
}