/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea01ddv;

import com.mycompany.tarea01ddv.ExecuteProgram;
import com.mycompany.tarea01ddv.ProgramParser;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Daniel Villatoro
 */
public class Tarea01DDV {
 
    public static void main(String[] args) throws IOException {
        String inputFileName = "D:\\Documents\\TEC\\2023\\tarea01\\codigo\\archivo\\file.asm";
                
        ProgramParser parser = new ProgramParser();
        ArrayList<int[]> instrucciones = null;
        
        try {
            instrucciones = parser.parse(inputFileName);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
        ExecuteProgram executeLoop = new ExecuteProgram();
        
        executeLoop.cargarPrograma(instrucciones);
        
        int step = 0;
        int i = 0;
        
        while(i<instrucciones.size()){
            if (step%2 == 0){
                executeLoop.fetch();
                System.out.println("Fetch instruccion #"+i);
                System.out.println(executeLoop.toString());
                System.out.println("\n-----------------------------------------\n");
            }else{
                executeLoop.execute();
                System.out.println("Execute instruccion #"+i);
                System.out.println(executeLoop.toString());
                System.out.println("\n-----------------------------------------\n");
                i++;
            }
            step++;
        }
        int stop=0;        
    }
}
