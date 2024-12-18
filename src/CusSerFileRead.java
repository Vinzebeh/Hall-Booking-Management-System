/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class CusSerFileRead extends TxtFileRead{
    private List<String[]> data;
    
    public CusSerFileRead(String filePath){
        super(filePath);
        data = new ArrayList<>();
    }
    
    public List<String[]> getData(){
        return data;
    }
    
    @Override
    public void processLine(String[] fileLine){        
        data.add(fileLine);
    }
}
