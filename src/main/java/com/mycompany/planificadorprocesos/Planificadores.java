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

    Lista<Proceso> listaP;
    
    public Planificadores(Lista<Proceso> lista) {
        listaP = lista;
    }
    
    Lista<Proceso> copiaLista = listaP;
    Lista<Proceso> procesosFinalizados;
    Lista<Proceso> procesosBloqueados;

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
    public Proceso ED(Lista<Proceso> listaP) {
        Proceso p = null;
        if (!listaP.esVacia()){
            if (listaP.getPrimero().getDato().Duracion == 0){
                listaP.getPrimero().getDato().Finalizado = true;
                procesosFinalizados.insertar(listaP.getPrimero());
                listaP.eliminar(listaP.getPrimero().getDato().Prioridad);
            }
            if (listaP.getPrimero().getDato().TEntreES == 0){
                listaP.getPrimero().getDato().bloqueado = true;
                procesosBloqueados.insertar(listaP.getPrimero());
                listaP.eliminar(listaP.getPrimero().getDato().Prioridad);
            }
            

        }
        return p;
    }

    public void SJF() {

    }
}
