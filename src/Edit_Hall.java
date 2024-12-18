
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ynning
 */
public class Edit_Hall {
    private String hallType;
    private String hallName;
    private String capacity;
    private String hallInfo;

    public Edit_Hall(String hallType, String hallName, String capacity, String hallInfo) {
        this.hallType = hallType;
        this.hallName = hallName;
        this.capacity = capacity;
        this.hallInfo = hallInfo;

        updateHallInfo();
    }

    private void updateHallInfo() {
       // Load existing hall data from Hall_info.txt
        List<String> hallData = loadHallData("Hall_info.txt");

        // Prepare new hall entry string
        String newHallData = String.join(";", hallType, hallName, capacity, hallInfo);

        // Find and update the existing hall entry based on hallName
        boolean hallUpdated = false;
        for (int i = 0; i < hallData.size(); i++) {
            String[] details = hallData.get(i).split(";");
            if (details.length > 1 && details[1].equalsIgnoreCase(hallName)) {
                hallData.set(i, newHallData); // Replace the existing entry with new data
                hallUpdated = true;
                break;
            }
        }

        if (hallUpdated) {
            saveHallData("Hall_info.txt", hallData);
            JOptionPane.showMessageDialog(null, "Hall information updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Hall not found in the data.");
        }

        // Update hall booking data
        updateHallBookingData(hallName, hallType, capacity, hallInfo);
    }

    private void saveHallData(String fileName, List<String> hallData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String data : hallData) {
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving data: " + e.getMessage());
        }
    }

    private void updateHallBookingData(String hallName, String hallType, String capacity, String hallInfo) {
        // Load existing booking data from hall_booking.txt
       List<String> bookingData = loadHallData("hall_booking.txt");

    // Check if the hall exists in booking data and update it
    boolean bookingUpdated = false;
    for (int i = 0; i < bookingData.size(); i++) {
        String[] details = bookingData.get(i).split(";");
        if (details.length > 1 && details[1].equalsIgnoreCase(hallName)) {
            // Keep the original booking status and dates
            bookingData.set(i, String.join(";", hallType, hallName, details[2], details[3], details[4], details[5]));
            bookingUpdated = true;
            break;
        }
    }

    // Optionally save the updated booking data
    if (bookingUpdated) {
        saveHallData("hall_booking.txt", bookingData);
    }
    }

    private List<String> loadHallData(String fileName) {
        List<String> hallData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                hallData.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
        return hallData;
    }
}
