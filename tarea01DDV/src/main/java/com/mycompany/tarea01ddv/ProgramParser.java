/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea01ddv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * Clase que se encarga de parsear el programa asm ingresado
 */
public class ProgramParser {
    
    /**
     * Metodo que procesa el archivo con el programa y que obtiene las instrucciones
     * y a su vez, se asegura de que la estructura del programa sea correcta.
     * 
     * @param path. Direccion del archivo con el programa a procesar.
     * @return Una lista con las instrucciones del programa procesado.
     * @throws IOException
     * @throws Exception 
     */
    public ArrayList<int[]> parse(String path) throws IOException, Exception{
        
        if(!path.endsWith(".asm")){
            throw new Exception("Input file has to be .asm");
        }
        
        String ASMProgram = Files.readString(Paths.get(path), Charset.defaultCharset());
        
        String[] instruc = ASMProgram.split("\n");
        
        ArrayList<int[]> instrucciones = formarInstrucciones(instruc);
        
        if(!isCorrectFormat(instrucciones)){
            throw new Exception("Program is formatted incorrectly");
        }
        
        return instrucciones;
    }
    
    /**
     * Procesa cada fila de instruccion del archivo y obtiene las diferentes partes
     * de la instruccion (operacion,direccionamiento,numero)
     * 
     * @param instruc
     * @return Una lista con las instrucciones del programa procesado.
     */
    private ArrayList<int[]> formarInstrucciones( String[] instruc){
        ArrayList<int[]> resultado = new ArrayList<>();
        
        for (String string : instruc) {
            String temp = string.trim().replace(",", "");
            String[] partesInstruccion = temp.split(" ");
            int instruccion = getCodigoComando(partesInstruccion[0]);
            int registro = getCodigoRegistro(partesInstruccion[1]);
            int valor = (partesInstruccion.length > 2)? Integer.parseInt(partesInstruccion[2]) : 0;
            int[] lineaCodigo = {instruccion,registro,valor};
            resultado.add(lineaCodigo);
        }
        
        return resultado;
    }
    
    /**
     * Se encarga de que las instrucciones del programa sean válidas
     * 
     * @param instrucciones
     * @return true si el formato es correcto
     */
    private boolean isCorrectFormat(ArrayList<int[]> instrucciones){
        for (int[] instruccion : instrucciones) {
            if(instruccion[0] == -1){
                return false;
            }
        }
        return true;
    }
    
    /***
     * Devuelve el código relacionado a la operación que se procesa.
     * 
     * @param nombre
     * @return entero que representa la operación
     */
    private int getCodigoComando(String nombre){
        int value = switch (nombre) {
            case "LOAD" -> 1;
            case "STORE" -> 2;
            case "MOV" -> 3;
            case "SUB" -> 4;
            case "ADD" -> 5;
            default -> -1;
        };
        return value;
    }
    
    /**
     * Devuelve el código relacionado a la representación en texto del registro
     * 
     * @param nombre
     * @return entero que representa el registro de CPU.
     */
    private int getCodigoRegistro(String nombre){
        int value = switch (nombre) {
            case "AX" -> 1;
            case "BX" -> 2;
            case "CX" -> 3;
            case "DX" -> 4;
            default -> -1;
        };
        return value;
    }
    
}
