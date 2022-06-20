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
    Lista<Proceso> ProcesosFinalizados = new Lista();
    Lista<Proceso> ProcesosBloqueados = new Lista();
    long[] TEntreES1;

    long[] DuracionES1;

//    Lista<Proceso> copiaLista1 = new Lista();
//    Lista<Proceso> copiaLista2 = new Lista();
//    Lista<Proceso> copiaLista3 = new Lista();
//    Lista<Proceso> copiaLista4 = new Lista();

    public Planificadores(long quantum, Lista<Proceso> lista1, Lista<Proceso> lista2, Lista<Proceso> lista3, Lista<Proceso> lista4, Lista<Proceso> procesosFinalizados, Lista<Proceso> procesosBloqueados) {
        listaP1 = lista1;
        listaP2 = lista2;
        listaP3 = lista3;
        listaP4 = lista4;
        Quantum = quantum;
        ProcesosFinalizados = procesosFinalizados;
        ProcesosBloqueados = procesosBloqueados;
        TEntreES1 = new long[listaP1.cantElementos() + listaP2.cantElementos() + listaP3.cantElementos() + listaP4.cantElementos()];

        DuracionES1 = new long[listaP1.cantElementos() + listaP2.cantElementos() + listaP3.cantElementos() + listaP4.cantElementos()];

        copiarTEntreES(TEntreES1, listaP1);

        copiarDuracionES(DuracionES1, listaP1);


//        copiaLista1 = copiarLista(listaP1);
//        copiaLista2 = copiarLista(listaP2);
//        copiaLista3 = copiarLista(listaP3);
//        copiaLista4 = copiarLista(listaP4);
    }

    int contadorEnvejecimiento = 0;

    Proceso procesoActivo = null;

    int indexProcesoActivo = 0;

    long quantumCounter = Quantum;

    private void copiarTEntreES(long[] valores, Lista<Proceso> lista) {

        Nodo<Proceso> actual = lista.getPrimero();
        while (actual != null) {
            valores[(int) actual.getDato().ID - 1] = actual.getDato().TEntreES;
            actual = actual.getSiguiente();
        }
    }

    private void copiarDuracionES(long[] valores, Lista<Proceso> lista) {

        Nodo<Proceso> actual = lista.getPrimero();
        while (actual != null) {
            valores[(int) actual.getDato().ID - 1] = actual.getDato().DuracionES;
            actual = actual.getSiguiente();
        }
    }

    public Proceso roundRobin(Lista<Proceso> listaP, int n) {
        long[] vTEntreES;
        long[] vDuacionES;
        if (n == 2) {
            vTEntreES = TEntreES1;
            vDuacionES = DuracionES1;
        } else {
            vTEntreES = TEntreES1;
            vDuacionES = DuracionES1;
        }
        ProcesosBloqueados.contadorDesbloqueoDesordenado(listaP, vTEntreES, vDuacionES);
        if (!listaP.esVacia()) {
            Proceso p = listaP.getPrimero().getDato();

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
        return null;
    }

    public Proceso evenDriven(Lista<Proceso> listaP) {
        ProcesosBloqueados.contadorDesbloqueoOrdenado(listaP, TEntreES1, DuracionES1);
        if (!listaP.esVacia()) {
            Proceso p = listaP.getPrimero().getDato();

            if (p != null) {

                p.Duracion--;
                if (p.TEntreES > 1) {
                    p.TEntreES--;
                }
                if (p.Duracion == 0) {
                    p.Finalizado = true;
                    Nodo<Proceso> nombre = listaP.getPrimero();
                    ProcesosFinalizados.insertar(nombre);
                    listaP.eliminar(p.ID);
                } else if (p.TEntreES == 1) {
                    p.bloqueado = true;
                    Nodo<Proceso> nodo = new Nodo(p.ID, p);
                    ProcesosBloqueados.insertar(nodo);
                    listaP.eliminar(p.ID);
                }

//                contadorEnvejecimiento++;
//                if (contadorEnvejecimiento == 2) {
//                    contadorEnvejecimiento = 0;
//                    listaP.bajarPrioridades();
//                }
            }

            return p;
        }
        return null;
    }

    public Proceso firstComeFirstServed(Lista<Proceso> listaP) {
        ProcesosBloqueados.contadorDesbloqueoDesordenado(listaP, TEntreES1, DuracionES1);
        if (!listaP.esVacia()) {
            Proceso p = listaP.getPrimero().getDato();

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
        return null;
    }
}
