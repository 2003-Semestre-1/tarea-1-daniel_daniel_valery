/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea01ddv;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 *Clase que se encarga de las etapas de ejecución de un programa. Incluye las 
 * etapas de fetch y execute.
 * 
 */
public class ExecuteProgram {
    private static final int MAX_MEMORY = 100;
    private int PC=0,AC=0;
    private int[] IR = null;
    private int[][] memoria = new int [MAX_MEMORY][];
    private int[] registros = {0,0,0,0};
    
    /**
     * Metodo que limpia las variables.
     */
    public void clear(){
        PC=0;
        AC=0;
        IR = null;
        memoria = new int [MAX_MEMORY][];
        registros = new int[]{0,0,0,0};
    }
    
    /**
     * Carga las instrucciones a memoria e inicializa el PC
     * 
     * @param instrucciones
     */
    public void cargarPrograma(ArrayList<int[]> instrucciones){
        int posToLoad=0;
        do {            
            posToLoad = new Random().nextInt(10, MAX_MEMORY);
        } while (posToLoad+instrucciones.size() >= MAX_MEMORY);
        
        for(int i = 0; i<instrucciones.size(); i++){
            memoria[posToLoad+i]=instrucciones.get(i);
        }
        
        PC = posToLoad-1;
    }
    
    /**
     * Ejecuta la etapa de fetch. aumenta en 1 el PC y carga en IR la instruccion
     * a ejecutar.
     */
    public void fetch(){
        PC = PC+1;
        IR = memoria[PC];
    }
    
    /**
     * Ejecuta la etapa de ejecución del CPU. Ejecuta la instruccion almacenada 
     * en el IR
     */
    public void execute(){
        switch (IR[0]) {
                case 1 -> AC = registros[IR[1]-1];
                case 2 -> registros[IR[1]-1] = AC;
                case 3 -> registros[IR[1]-1] = IR[2];
                case 4 -> AC = AC-registros[IR[1]-1];
                case 5 -> AC = AC+registros[IR[1]-1];
            }
    }

    public int getPC() {
        return PC;
    }

    public int getAC() {
        return AC;
    }

    public int[] getIR() {
        return IR;
    }

    public int[][] getMemoria() {
        return memoria;
    }

    public int[] getRegistros() {
        return registros;
    }

    @Override
    public String toString() {
        return "Registros : AX:"+this.registros[0]+" BX: "+this.registros[1]+" CX: "+this.registros[2]+" DX: "+this.registros[3]+"\n"+"AC: "+this.AC;
    }
    
    
    
}
