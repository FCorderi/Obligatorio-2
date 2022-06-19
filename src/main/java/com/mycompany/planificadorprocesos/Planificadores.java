/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planificadorprocesos;

import java.util.Vector;
import java.io.IOException;

/**
 *
 * @author Felipe
 */
public class Planificadores {

    Lista<Proceso> listaP1;
    Lista<Proceso> listaP2;
    Lista<Proceso> listaP3;
    Lista<Proceso> listaP4;

    public Planificadores(Lista<Proceso> lista1, Lista<Proceso> lista2, Lista<Proceso> lista3, Lista<Proceso> lista4) {
        listaP1 = lista1;
        listaP2 = lista2;
        listaP3 = lista3;
        listaP4 = lista4;
    }

    Lista<Proceso> copiaLista1 = listaP1;
    Lista<Proceso> copiaLista2 = listaP2;
    Lista<Proceso> copiaLista3 = listaP3;
    Lista<Proceso> copiaLista4 = listaP4;
    Lista<Proceso> procesosFinalizados;
    Lista<Proceso> procesosBloqueados;
    int contadorEnvejecimiento = 0;

    Proceso procesoActivo = null;

    int indexProcesoActivo = 0;

    long quantum = 4;

    long quantumCounter = quantum;

    /*
    public void RR(Lista<Proceso> que) {

        try {
            if(procesoActivo == null){
                procesoActivo = que.getPrimero();
            }
            if (procesoActivo.Finalizado || quantumCounter == 0 || procesoActivo.bloqueado) {
                Proceso proxProceso = null;
                int index = 0;

                if (indexProcesoActivo >= (que. - 1)) {
                    index = 0;
                } else if (procesoActivo != null && procesoActivo.Finalizado) {
                    index = indexProcesoActivo +1;
                } else {
                    index = (indexProcesoActivo + 1);
                }

                proxProceso = que.get(index);
                procesoActivo = proxProceso;
                indexProcesoActivo = index;

                quantumCounter = quantum;
            }
            quantumCounter--;
        } catch (NullPointerException e) {
        }
    }
     */
    public Proceso evenDriven(Lista<Proceso> listaP) {
        Proceso p = listaP.getPrimero().getDato();

        procesosBloqueados.contadorDesbloqueoOrdenado(listaP, copiaLista1);

        p.Duracion--;
        if (p.Duracion == 0) {
            p.Finalizado = true;
            procesosFinalizados.insertar(listaP.getPrimero());
            listaP.eliminar(p.ID);
        }

        p.TEntreES--;
        if (p.TEntreES == 0) {
            p.bloqueado = true;
            procesosBloqueados.insertar(listaP.getPrimero());
            listaP.eliminar(p.ID);
        }

        contadorEnvejecimiento++;
        if (contadorEnvejecimiento == 2) {
            contadorEnvejecimiento = 0;
            listaP.bajarPrioridades();
        }

        return p;
    }

    public void firstComeFirstServed(Lista<Proceso> listaP) {
        Proceso p = listaP.getPrimero().getDato();
        
        procesosBloqueados.contadorDesbloqueoDesordenado(listaP, copiaLista4);
        
        p.Duracion--;
        if (p.Duracion == 0) {
            p.Finalizado = true;
            procesosFinalizados.insertar(listaP.getPrimero());
            listaP.eliminar(p.ID);
        }

        p.TEntreES--;
        if (p.TEntreES == 0) {
            p.bloqueado = true;
            procesosBloqueados.insertar(listaP.getPrimero());
            listaP.eliminar(p.ID);
        }
    }
}
