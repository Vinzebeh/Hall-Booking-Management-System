/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yonning_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ynning
 */
public class View_Hall {
    // Method to load and display hall information from the file into the table
    public void loadHallData(JTable hallTable) {
        try {
            DefaultTableModel model = (DefaultTableModel) hallTable.getModel();
            model.setRowCount(0);  // Clear existing rows in the table

            FileReader fr = new FileReader("Hall_info.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;

            // Read data from the file and populate the table
            while ((read = br.readLine()) != null) {
                String[] hallDetails = read.split(";");
                if (hallDetails.length >= 4) {
                    String hallType = hallDetails[0];
                    String hallName = hallDetails[1];
                    String capacity = hallDetails[2];
                    String hallInfo = hallDetails[3];

                    // Add each hall entry to the table
                    model.addRow(new Object[]{hallType, hallName, capacity, hallInfo});
                }
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading data: " + e.getMessage());
        }
    }
}
