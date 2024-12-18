/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author USER
 */
abstract class TxtFileRead {
    protected String filePath;
    
    protected TxtFileRead(String filePath){
        this.filePath = filePath;
    }
    
    public void readFile() throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fileLine = line.split(";");
                processLine(fileLine);
            }
        }
    }
        
    public abstract void processLine(String[] fileLine);    
}
