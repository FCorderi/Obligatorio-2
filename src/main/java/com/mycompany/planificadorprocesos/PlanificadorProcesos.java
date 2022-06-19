/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.planificadorprocesos;

import java.util.Vector;

/**
 *
 * @author Felipe
 */
public class PlanificadorProcesos {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //new InterfazInicial().setVisible(true);
        
        Proceso p1 = new Proceso(1, "SO", 10, 4, 2, 20);
        Proceso p2 = new Proceso(2, "SO", 10, 4, 2, 20);
        
        Nodo<Proceso> n1 = new Nodo(p1.ID, p1);
        Nodo<Proceso> n2 = new Nodo(p2.ID, p2);
        Lista<Proceso> procesos = new Lista<>();
        procesos.insertarOrdenado(n1);
        procesos.insertarOrdenado(n2);
    /*  Planificadores p = new Planificadores(procesos);
        for (int i = 0; i < 20; i++) {
            p.RR(procesos);
            if (p.procesoActivo != null) {
                System.out.println("Proceso activo: " + p.procesoActivo.GetID());
                System.out.println("Index: " + p.indexProcesoActivo);
            }
            if (i == 9){
                procesos.get(0).Finalizado = true;
            }

        }
    */
        Nodo<Proceso> actual = procesos.getPrimero();
        Nodo<Proceso> aux;

        System.out.println("actual" + actual.getDato().Duracion);
        System.out.println("-1");
        actual.getDato().Duracion--;
        System.out.println("actual" + actual.getDato().Duracion);
        System.out.println("primero" + procesos.getPrimero().getDato().Duracion);


    }
}
