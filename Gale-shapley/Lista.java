/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mati
 */
public class Lista {

    private Nodo cabecera;
    private int longitud;

    public Lista() {
        cabecera = null;
        longitud = 0;
    }

    public boolean insertar(Object elem, int pos) {
        boolean res = false;
        Nodo nodoAux = cabecera;
        int aux = 1;
        if (pos > 0 && pos < longitud + 2) {
            if (pos == 1) { // AÑADIMOS EN LA PRIMER POSICION
                Nodo nodoInsert = new Nodo(elem, null);
                nodoInsert.setEnlace(cabecera);
                cabecera = nodoInsert;
                longitud = longitud + 1;
                res = true;
            } else {
                if (longitud + 1 == pos) { // AÑADIMOS EN EL FINAL
                    Nodo nodoInsert = new Nodo(elem, null);
                    while (aux < pos - 1) {
                        nodoAux = nodoAux.getEnlace();
                        aux++;
                    }
                    nodoAux.setEnlace(nodoInsert);
                    longitud = longitud + 1;
                    res = true;
                } else { // AÑADIMOS EN FORMA GENERICA (MEDIO )
                    Nodo nodoInsert = new Nodo(elem, null);
                    while (aux < pos - 1) {
                        nodoAux = nodoAux.getEnlace();
                        aux++;
                    }
                    nodoInsert.setEnlace(nodoAux.getEnlace());
                    nodoAux.setEnlace(nodoInsert);

                    res = true;
                    longitud++;
                }

            }

        }
        return res;
    }

    public void vaciar() {
        cabecera = null;
        longitud = 0;
    }

    public boolean esVacia() {
        boolean res = false;
        if (cabecera == null) {
            res = true;
        }
        return res;
    }

    public Object recuperar(int pos) {
        Object recuperado;
        int aux = 1;
        Nodo nodoAux = cabecera;
        if (pos > 0 && pos <= longitud) {
            while (aux < pos) {
                nodoAux = nodoAux.getEnlace();
                aux++;
            }
            recuperado = nodoAux.getElem();
        } else {
            recuperado = null;
        }
        return recuperado;
    }

    public int localizar(Object busca) {
        int pos = -1;
        boolean encontrado = false;
        int aux = 1;
        Nodo nodoAux = cabecera;
        while ((!encontrado) && (aux <= longitud)) {

            if (nodoAux.getElem() == busca) {
                pos = aux;
                encontrado = true;
            }
            nodoAux = nodoAux.getEnlace();
            aux++;
        }
        return pos;
    }

    public boolean eliminar(int pos) {
        boolean res = false;
        int aux = 1;
        Nodo nodoAux = cabecera;

        if (pos > 0 && pos <= longitud) {
            if (pos == 1 && longitud == 1) {
                longitud = 0;
                cabecera = null;
                res = true;
            } else {
                if (pos == 1) {
                    cabecera = cabecera.getEnlace();
                    longitud--;
                    res = true;
                } else {
                    while (aux < pos - 1) {
                        aux++;
                        nodoAux = nodoAux.getEnlace();
                    }
                    res = true;
                    longitud--;
                    nodoAux.setEnlace((nodoAux.getEnlace().getEnlace()));
                }
            }

        }
        return res;

    }

    public int longitud() {
        return longitud;
    }

    public Lista clone() {

        Lista clonada = new Lista();
        if (cabecera != null) {
            Nodo cabe = new Nodo(cabecera.getElem(), null);
            clonada.cabecera = cabe;
            clonada.longitud = 1;
            Nodo recorrido = cabecera.getEnlace();
            while (recorrido != null) {
                Nodo aux3 = new Nodo(recorrido.getElem(), null);
                cabe.setEnlace(aux3);
                cabe = cabe.getEnlace();
                recorrido = recorrido.getEnlace();
                clonada.longitud++;
            }
        }
        return clonada;
    }

    public String toString() {
        String s = "";
        if (this.cabecera == null) {
            s = "Lista vacia";
        } else {
            // se ubica para recorrer la cola
            Nodo aux = this.cabecera;
            s = "[";
            while (aux != null) {
                // agrega texto del elem y avanza
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ",";
                }
            }
            s += "]";
        }
        return s; // return the string here
    }

    public void invertir() {
        // sin crear nodos auxiliares
        if (longitud == 2) {
            Nodo auxInt1 = cabecera;
            Nodo auxInt2 = cabecera.getEnlace();

            auxInt2.setEnlace(auxInt1);
            auxInt1.setEnlace(null);

            cabecera = auxInt2;
        } else {
            if (longitud > 2) {
                Nodo a = cabecera;
                Nodo b = cabecera.getEnlace();
                Nodo d = cabecera;
                while (a.getEnlace() != null) {
                    d = cabecera;
                    cabecera = b;
                    a.setEnlace(b.getEnlace());
                    b.setEnlace(d);

                    b = a.getEnlace();

                }

            }
        }

    }

    public void eliminarApariciones(Object x) {

        while (cabecera != null && cabecera.getElem() == x) {
            cabecera = cabecera.getEnlace();
            longitud--;
        }
        Nodo aux = cabecera;
        if (cabecera != null) {
            while (aux.getEnlace() != null) {
                if (aux.getEnlace().getElem() == x) {
                    aux.setEnlace(aux.getEnlace().getEnlace());
                    longitud--;
                } else {
                    aux = aux.getEnlace();
                }
            }
        }

    }

    // metodos del simulacro parcial 1
    public Lista obtenerMultiplos(int n) {
        Lista multiplos = new Lista();
        int aux = n;
        int i = 1;
        Nodo nodito = cabecera;
        if (n > 0) {

            while (aux <= longitud) {
                while (i != n) {
                    if (nodito != null) {
                        nodito = nodito.getEnlace();

                    }
                    i++;
                }
                System.out.println(nodito.getElem());
                multiplos.insertar(nodito.getElem(), multiplos.longitud + 1);
                aux = aux + n;
                i = 1;
                nodito = nodito.getEnlace();
            }
        }
        return multiplos;
    }
}
