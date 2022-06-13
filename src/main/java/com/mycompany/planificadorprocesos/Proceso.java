/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planificadorprocesos;

/**
 *
 * @author Felipe
 */
public class Proceso {
    
    String Nombre;
    
    String Tipo;
    
    int Duracion;
    
    int TEntreES;
    
    int DuracionES;
    
    int Prioridad;
    
    public Proceso(String nombre, String tipo, int duracion, int tEntreES, int duracionES, int prioridad){
        Nombre = nombre;
        Tipo = tipo;
        Duracion = duracion;
        TEntreES = tEntreES;
        DuracionES = duracionES;
        Prioridad = prioridad;
    }
}
