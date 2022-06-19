/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planificadorprocesos;

/**
 *
 * @author Felipe
 */
public class Planificadores {

    Lista<Proceso> listaP1;
    Lista<Proceso> listaP2;
    Lista<Proceso> listaP3;
    Lista<Proceso> listaP4;
    long Quantum;
    Lista<Proceso> ProcesosFinalizados;
    Lista<Proceso> ProcesosBloqueados = new Lista ();

    public Planificadores(long quantum, Lista<Proceso> lista1, Lista<Proceso> lista2, Lista<Proceso> lista3, Lista<Proceso> lista4, Lista<Proceso> procesosFinalizados, Lista<Proceso> procesosBloqueados) {
        listaP1 = lista1;
        listaP2 = lista2;
        listaP3 = lista3;
        listaP4 = lista4;
        Quantum = quantum;
        ProcesosFinalizados = procesosFinalizados;
        ProcesosFinalizados = procesosBloqueados;
    }

    Lista<Proceso> copiaLista1 = listaP1;
    Lista<Proceso> copiaLista2 = listaP2;
    Lista<Proceso> copiaLista3 = listaP3;
    Lista<Proceso> copiaLista4 = listaP4;
    
    int contadorEnvejecimiento = 0;

    Proceso procesoActivo = null;

    int indexProcesoActivo = 0;

    long quantumCounter = Quantum;

    public Proceso roundRobin(Lista<Proceso> listaP, int n) {
        Proceso p = listaP.getPrimero().getDato();
        Lista<Proceso> copiaLista;
        if (n == 2) {
            copiaLista = copiaLista2;
        } else {
            copiaLista = copiaLista3;
        }
        ProcesosBloqueados.contadorDesbloqueoDesordenado(listaP, copiaLista);

        if (p != null) {
            p.Duracion--;
            p.TEntreES--;

            if (p.Duracion == 0) {
                p.Finalizado = true;
                ProcesosFinalizados.insertar(listaP.getPrimero());
                listaP.eliminar(p.ID);
                quantumCounter = Quantum;
            } else if (p.TEntreES == 1) {
                p.bloqueado = true;
                ProcesosBloqueados.insertar(listaP.getPrimero());
                listaP.eliminar(p.ID);
                quantumCounter = Quantum;
            } else if (quantumCounter == 0) {
                Nodo<Proceso> nodo = new Nodo(p.ID, p);
                listaP.eliminar(p.ID);
                listaP.insertar(nodo);
                quantumCounter = Quantum;
            }

            quantumCounter--;
        }
        return p;
    }

    public Proceso evenDriven(Lista<Proceso> listaP) {
        Proceso p = listaP.getPrimero().getDato();

        ProcesosBloqueados.contadorDesbloqueoOrdenado(listaP, copiaLista1);

        if (p != null) {

            p.Duracion--;
            if (p.TEntreES > 1) {
                p.TEntreES--;
            }
            if (p.Duracion == 0) {
                p.Finalizado = true;
                ProcesosFinalizados.insertar(listaP.getPrimero());
                listaP.eliminar(p.ID);
            } else if (p.TEntreES == 1) {
                p.bloqueado = true;
                ProcesosBloqueados.insertar(listaP.getPrimero());
                listaP.eliminar(p.ID);
            }

            contadorEnvejecimiento++;
            if (contadorEnvejecimiento == 2) {
                contadorEnvejecimiento = 0;
                listaP.bajarPrioridades();
            }
        }

        return p;
    }

    public Proceso firstComeFirstServed(Lista<Proceso> listaP) {
        Proceso p = listaP.getPrimero().getDato();

        ProcesosBloqueados.contadorDesbloqueoDesordenado(listaP, copiaLista4);

        if (p != null) {
            p.Duracion--;
            if (p.TEntreES > 1) {
                p.TEntreES--;
            }
            if (p.Duracion == 0) {
                p.Finalizado = true;
                ProcesosFinalizados.insertar(listaP.getPrimero());
                listaP.eliminar(p.ID);
            } else if (p.TEntreES == 1) {
                p.bloqueado = true;
                ProcesosBloqueados.insertar(listaP.getPrimero());
                listaP.eliminar(p.ID);
            }
        }

        return p;
    }
}
