package arbolgrafico;

import gui.ArbolGrafico;
import java.util.LinkedList;
import javax.swing.JPanel;

public class Arbol {

    private Nodo raiz;
    int cant;

    public Nodo getRaiz() {
        return this.raiz;
    }

    public void setRaiz(Nodo r) {
        this.raiz = r;
    }

    public Arbol() {
        this.raiz = null;
    }

    /**
     * Metodo para agregar datos
     * @param dato: dato que se va a agregar
     * @return 
     */
    public boolean agregar(int dato) {
        Nodo nuevo = new Nodo(dato, null, null);
        insertar(nuevo, raiz);
        return true;
    }

    public void insertar(Nodo nuevo, Nodo pivote) {
        if (this.raiz == null) {
            raiz = nuevo;
        } else {
            if (nuevo.getDato() <= pivote.getDato()) {
                if (pivote.getIzq() == null) {
                    pivote.setIzq(nuevo);
                } else {
                    insertar(nuevo, pivote.getIzq());
                }
            } else {
                if (pivote.getDer() == null) {
                    pivote.setDer(nuevo);
                } else {
                    insertar(nuevo, pivote.getDer());
                }
            }
        }
    }
    
    /**
     * Metodo recursivo para obtener la cantidad de Nodos
     * @return cant
     */
    public String cantidadNodos() {
        cant = 0;
        cantidadNodos(raiz);
        return ""+cant;
    }
    private void cantidadNodos(Nodo reco) {
        if (reco != null) {
            cant++;
            cantidadNodos(reco.getIzq());
            cantidadNodos(reco.getDer());
        }
    }
    
    /**
     * Metodo recursivo para eliminar un Nodo
     * @param x: valor del nodo que va a ser eliminado
     * @return x
     */
    public int eliminar(int x) {
        if (!this.buscar(x)) {
            return 0;
        }
        Nodo y = eliminar(this.raiz, x);
        this.setRaiz(y);
        return x;
    }
    private Nodo eliminar(Nodo r, int x) {
        if (r == null) {
            return null;//<--Dato no encontrado		
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            r.setIzq(eliminar(r.getIzq(), x));
        } else if (compara < 0) {
            r.setDer(eliminar(r.getDer(), x));
        } else {
            System.out.println("Encontro el dato:" + x);
            if (r.getIzq() != null && r.getDer() != null) {
                /*
                 * Buscar el menor de los derechos y lo intercambia por el dato
                 * que desea borrar.
                 */
                Nodo cambiar = buscarMin(r.getDer());
                int aux = cambiar.getDato();
                cambiar.setDato(r.getDato());
                r.setDato(aux);
                r.setDer(eliminar(r.getDer(), x));
                System.out.println("2 ramas");
            } else {
                r = (r.getIzq() != null) ? r.getIzq() : r.getDer();
                System.out.println("Entro cuando le faltan ramas ");
            }
        }
        return r;
    }
    @SuppressWarnings("empty-statement")
    private Nodo buscarMin(Nodo r) {
        for (; r.getIzq() != null; r = r.getIzq());
        return (r);
    }

    /**
     * Metodo recursivo para buscar un Nodo
     * @param x: valor del nodo que está siendo buscado
     * @return 
     */
    public boolean buscar(int x) {
        return (buscar(this.raiz, x));
    }
    private boolean buscar(Nodo r, int x) {
        if (r == null) {
            return (false);
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            return (buscar(r.getIzq(), x));
        } else if (compara < 0) {
            return (buscar(r.getDer(), x));
        } else {
            return (true);
        }
    }

    /**
     * Metodo recursivo para recorrer el arbol en PreOrden
     * @return rec
     */
    public LinkedList preOrden() {
        LinkedList rec = new LinkedList();
        preOrden(raiz,rec);
        return rec;
    }
    private void preOrden(Nodo reco, LinkedList rec) {
        if (reco != null) {
            rec.add(reco.getDato() + " ");
            preOrden(reco.getIzq(),rec);
            preOrden(reco.getDer(),rec);
        }
    }
    
    /**
     * Metodo recursivo para recorrer el arbol en InOrden
     * @return rec
     */
    public LinkedList inOrden() {
        LinkedList rec = new LinkedList();
        inOrden(raiz,rec);
        return rec;
    }
    private void inOrden(Nodo reco,LinkedList rec) {
        if (reco != null) {
            inOrden(reco.getIzq(),rec);
            rec.add(reco.getDato() + " ");
            inOrden(reco.getDer(),rec);
        }
    }

    /**
     * Metodo recursivo para recorrer el arbol en PostOrden
     * @return rec
     */
    public LinkedList postOrden() {
        LinkedList rec = new LinkedList();
        postOrden(raiz,rec);
        return rec;
    }
    private void postOrden(Nodo reco, LinkedList rec) {
        if (reco != null) {
            postOrden(reco.getIzq(),rec);
            postOrden(reco.getDer(),rec);
            rec.add(reco.getDato() + " ");
        }
    }
    
    /**
     * Metodo para dibujar el arbol llamando a la clase ArbolGrafico
     * @return ArbolGrafico
     */
     public JPanel getdibujo() {
        return new ArbolGrafico(this);
    }
}
