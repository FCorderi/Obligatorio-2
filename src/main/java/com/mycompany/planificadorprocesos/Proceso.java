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
    
    long ID;
    
    String Tipo;
    
    long Duracion;
    
    long TEntreES;
    
    long DuracionES;
    
    int Prioridad;
    
    boolean Finalizado = false;
    
    boolean bloqueado = false;
    
    public Proceso( int id, String tipo, long duracion, long tEntreES, long duracionES, int prioridad){
        ID = id;
        Tipo = tipo;
        Duracion = duracion;
        TEntreES = tEntreES;
        DuracionES = duracionES;
        Prioridad = prioridad;
    }
    
    public long GetID(){
        return ID;
    }
    
    public String GetTipo(){
        return Tipo;
    }
    
    public long GetDuracion(){
        return Duracion;
    }
    
    public long GetTEntreES(){
        return TEntreES;
    }
    
    public long GetDuracionES(){
        return DuracionES;
    }
    
    public int GetPrioridad(){
        return Prioridad;
    }
}
