package arbolgrafico;

import java.util.LinkedList;
import javax.swing.JPanel;

public class SimuladorArbolBinario {

    Arbol miArbol = new Arbol();

    public SimuladorArbolBinario() {
    }

    public boolean insertar(Integer dato) {
        return (this.miArbol.agregar(dato));
    }

    public String borrar(Integer dato) {
        Integer x = this.miArbol.eliminar(dato);
        if (x == null) {
            return ("El dato no existe en el arbol");
        }
        return ("Dato borrado: " + x.toString());
    }

    public String preOrden() {
        LinkedList it = this.miArbol.preOrden();
        return (recorrido(it, "Recorrido PreOrden:"));
    }

    public String inOrden() {
        LinkedList it = this.miArbol.inOrden();
        return (recorrido(it, "Recorrido InOrden:"));
    }

    public String postOrden() {
        LinkedList it = this.miArbol.postOrden();
        return (recorrido(it, "Recorrido PosOrden:"));
    }

    public String buscar(Integer dato) {
        boolean siEsta = this.miArbol.buscar(dato);
        String r = "El dato:" + dato.toString() + "\n";
        r += siEsta ? "Si se encuentra en el arbol" : "No se encuentra en el arbol";
        return (r);
    }

    private String recorrido(LinkedList it, String cadena) {
        int i = 0;
        String r = cadena + "\n";
        while (i < it.size()) {
            r += it.get(i).toString() + " -> ";
            i++;
        }
        return (r);
    }
    
    public String CantidadNodos(){
        return this.miArbol.cantidadNodos();
    }
    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
}
