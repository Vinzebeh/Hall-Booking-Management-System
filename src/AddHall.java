
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ynning
 */
public class AddHall {

    private static final String HALL_INFO_FILE = "Hall_info.txt";
    private static final String HALL_BOOKING_FILE = "hall_booking.txt";

    // Method to add a hall to the system
    public void addHall(ArrayList<String> hallData) throws IOException {
        // Validate data
        if (!validateHallData(hallData)) {
            return;
        }

        // Check if hall exists
        if (hallExists(hallData.get(1))) {
            JOptionPane.showMessageDialog(null, "Error: Hall already exists.", "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If hall doesn't exist, write data to files
        saveHallData(hallData);
        JOptionPane.showMessageDialog(null, "Hall details successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to validate hall data
    private boolean validateHallData(ArrayList<String> hallData) {
        for (String field : hallData) {
            if (field == null || field.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: One or more fields are empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    // Method to check if a hall already exists
    private boolean hallExists(String hallName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(HALL_INFO_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] hallDetails = line.split(";");
                if (hallDetails.length >= 2 && hallDetails[1].equals(hallName)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to save hall data to files
    private void saveHallData(ArrayList<String> hallData) throws IOException {
        // Save to Hall_info.txt
        try (FileWriter fw = new FileWriter(HALL_INFO_FILE, true)) {
            fw.write(String.join(";", hallData) + ";" +  ";\n");
        }

        // Save to hall_booking.txt
        try (FileWriter fw = new FileWriter(HALL_BOOKING_FILE, true)) {
            fw.write(hallData.get(0) + ";" + hallData.get(1) + ";" + "Available" + ";" + "-" + ";" + "-" + ";" + "-" + ";\n");
        }
    }
}