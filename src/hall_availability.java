
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ynning
 */
public class hall_availability {
    
    private static final String HALL_BOOKING_FILE = "hall_booking.txt";

    public boolean updateHall(String hallType, String hallName, String startDate, String endDate, String remarks, String newStatus) {
        // Read the existing file contents
        ArrayList<String[]> hallBookingData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(HALL_BOOKING_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] hallDetails = line.split(";");
                hallBookingData.add(hallDetails);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + e.getMessage());
        }

        // Update the status, start date, and end date of the selected hall in the list
        boolean hallUpdated = false;
        for (String[] hallDetails : hallBookingData) {
            if (hallDetails.length >= 3 && hallDetails[0].equals(hallType) && hallDetails[1].equals(hallName)) {
                hallDetails[2] = newStatus;  // Update the status
                
                // Ensure array can hold start and end dates
                if (hallDetails.length < 6) {
                    hallDetails = java.util.Arrays.copyOf(hallDetails, 6);
                }

                // Update start and end dates
                hallDetails[3] = startDate;
                hallDetails[4] = endDate;
                hallDetails[5] = remarks;

                hallUpdated = true;
                break;
            }
        }

        if (!hallUpdated) {
            throw new RuntimeException("Error: Unable to find the selected hall in the file.");
        }

        // Write the updated data back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HALL_BOOKING_FILE))) {
            for (String[] hallDetails : hallBookingData) {
                bw.write(String.join(";", hallDetails));  // Join the updated details and write them to the file
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the file: " + e.getMessage());
        }

        return true;  // Return true when the update is successful
    }
    
}
