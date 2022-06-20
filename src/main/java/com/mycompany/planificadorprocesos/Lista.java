/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planificadorprocesos;

/**
 *
 * @author Felipe
 */
public class Lista<Proceso> implements ILista<Proceso> {

    private Nodo<Proceso> primero;
    private int cantidadElementos = 0;

    public Lista() {
        primero = null;
    }

    // implementar los metodos indicados en la interfaz
    public void bajarPrioridades() {
        Nodo<Proceso> actual = primero;
        if (actual.getDato().Prioridad > 1) {
            actual.getDato().Prioridad--;
        }
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            if (actual.getDato().Prioridad > 1) {
                actual.getDato().Prioridad--;
            }
        }
    }

    public void bloquear(Nodo<Proceso> p, Lista<Proceso> lista) {
        p.getDato().bloqueado = true;
        Nodo<Proceso> nodo = new Nodo(p.getDato().ID, p.getDato());
        insertar(nodo);
        lista.eliminar(p.getDato().ID);
    }

    public void contadorDesbloqueoOrdenado(Lista<Proceso> lista, long[] vTEntreES, long[] vDuracionES) {
        Nodo<Proceso> aux = null;
        Nodo<Proceso> sig = null;
        Nodo<Proceso> actual = primero;

        while (actual != null) {
            if (actual.getDato().DuracionES > 0) {
                actual.getDato().DuracionES--;
            }
            if (actual.getDato().DuracionES == 0) {
                actual.getDato().TEntreES = vTEntreES[(int) actual.getDato().ID - 1];
                actual.getDato().DuracionES = vDuracionES[(int) actual.getDato().ID - 1];
                Nodo<Proceso> nodo = new Nodo(actual.getDato().ID, actual.getDato());
                nodo.getDato().bloqueado = false;
                lista.insertarOrdenado(nodo);
                sig = actual.getSiguiente();
                eliminar(actual.getDato().ID);
                actual = sig;
            } else {
                actual = actual.getSiguiente();
            }
        }

    }

    public void contadorDesbloqueoDesordenado(Lista<Proceso> lista, long[] vTEntreES, long[] vDuracionES) {
        Nodo<Proceso> aux = null;
        Nodo<Proceso> sig = null;
        Nodo<Proceso> actual = primero;

        while (actual != null) {
            if (actual.getDato().DuracionES > 0) {
                actual.getDato().DuracionES--;
            }
            if (actual.getDato().DuracionES == 0) {
                actual.getDato().TEntreES = vTEntreES[(int) actual.getDato().ID - 1];
                actual.getDato().DuracionES = vDuracionES[(int) actual.getDato().ID - 1];
                Nodo<Proceso> nodo = new Nodo(actual.getDato().ID, actual.getDato());
                nodo.getDato().bloqueado = false;
                lista.insertar(nodo);
                sig = actual.getSiguiente();
                eliminar(actual.getDato().ID);
                actual = sig;
            } else {
                actual = actual.getSiguiente();
            }
        }
    }

    @Override
    public void insertar(Nodo<Proceso> nodo) {

        //nodo.setSiguiente(null);
        if (esVacia()) {
            primero = nodo;

            cantidadElementos = 1;

        } else {
            Nodo<Proceso> actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo);
            cantidadElementos++;
        }

    }

    public void insertarOrdenado(Nodo nodo) {
        Nodo<Proceso> actual = null;
        Nodo<Proceso> aux = null;
        if (esVacia()) {
            primero = nodo;
            cantidadElementos = 1;
        } else {
            if (primero.getDato().Prioridad > nodo.getDato().Prioridad) {
                nodo.setSiguiente(primero);
                primero = nodo;
                cantidadElementos++;
            } else {
                actual = primero;
                while (actual.getSiguiente() != null && actual.getSiguiente().getDato().Prioridad <= nodo.getDato().Prioridad) {
                    actual = actual.getSiguiente();
                }
                aux = actual.getSiguiente();
                nodo.setSiguiente(aux);
                actual.setSiguiente(nodo);
                cantidadElementos++;
            }
        }
    }

    @Override
    public Nodo<Proceso> buscar(Comparable clave) {
        if (esVacia()) {
            return null;
        }

        Nodo<Proceso> actual = primero;
        while (actual != null) {
            if (actual.getEtiqueta().equals(clave)) {
                return actual;
            }
            actual = actual.getSiguiente();

        }
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        if (esVacia()) {
            return false;
        }

        // caso especial para eliminar la cabeza de la lista encadenada
        if (primero.getEtiqueta().equals(clave)) {
            Nodo<Proceso> elQueSigue = primero.getSiguiente();
            primero.setSiguiente(null);
            primero = elQueSigue;
            cantidadElementos--;
            return true;
        }

        Nodo<Proceso> actual = primero;
        Nodo<Proceso> anterior = primero;
        while (actual != null) {
            if (actual.getEtiqueta().equals(clave)) {
                anterior.setSiguiente(actual.getSiguiente());
                actual.setSiguiente(null);
                cantidadElementos--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();

        }
        return false;
    }

    @Override
    public String imprimir() {
        String datos = "";
        Nodo<Proceso> actual = primero;
        while (actual != null) {
            datos = datos + "\n" + actual.getEtiqueta().toString();
            actual = actual.getSiguiente();
        }
        return datos;

    }

    @Override
    public String imprimir(String separador) {
        String datos = "";
        Nodo<Proceso> actual = primero;
        while (actual != null) {
            datos = datos + actual.getEtiqueta().toString() + separador;
            actual = actual.getSiguiente();
        }
        return datos;
    }

    @Override
    public int cantElementos() {
        return this.cantidadElementos;
    }

    @Override
    public boolean esVacia() {
        return primero == null;

    }

    @Override
    public void setPrimero(Nodo<Proceso> unNodo) {
        if (esVacia()) {
            primero = unNodo;
            cantidadElementos = 1;
        } else {
            unNodo.setSiguiente(primero);
            primero = unNodo;
            cantidadElementos++;

        }
    }

    public Nodo<Proceso> getPrimero() {
        return primero;
    }
}
