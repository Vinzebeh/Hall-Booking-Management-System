
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class CustomerClass {
    
    private static final String FILE_PATH = "Booking.txt";
    private static final String PAYMENT_FILE = "Payment.txt";
    
    public CustomerClass(){}
    
    public void updateBookingStatusInBookingFile(String bookingID) {
        StringBuilder fileContent = new StringBuilder(); 
        File file = new File(FILE_PATH);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isUpdated = false;  
            while ((line = br.readLine()) != null) { 
                String[] dataRow = line.split(";");  
                if (dataRow[7].equals(bookingID)) {  
                    dataRow[11] = "Canceled"; 
                    System.out.println("Modified row in Booking.txt: " + String.join(";", dataRow));
                    line = String.join(";", dataRow);
                    isUpdated = true;
                }
                fileContent.append(line).append("\n");
            }

            if (!isUpdated) {
                System.out.println("No matching Booking ID found in Booking.txt: " + bookingID);
            }

        } catch (IOException ex) {
            System.out.println("Error reading Booking.txt: " + ex.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            System.out.println("Preparing to write to Booking.txt...");
            bw.write(fileContent.toString());
            bw.flush(); 
            System.out.println("Booking.txt updated successfully");
        } catch (IOException ex) {
            System.out.println("Error writing to Booking.txt: " + ex.getMessage());
        }

        JOptionPane.showMessageDialog(null, "Booking has been successfully canceled in Booking.txt.");
    }

    public void updateBookingStatusInPaymentFile(String bookingID) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PAYMENT_FILE));
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                String[] data = line.split(";");
                if (data[2].trim().equals(bookingID.trim())) {
                    data[7] = "Canceled"; 
                    String updatedLine = String.join(";", data);
                    updatedLines.add(updatedLine);
                    System.out.println("Modified row in Payment.txt: " + updatedLine);
                } else {
                    updatedLines.add(line); 
                }
            }

            Files.write(Paths.get(PAYMENT_FILE), updatedLines);
            System.out.println("Booking ID: " + bookingID + " has been successfully canceled in Payment.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error updating the payment status in Payment.txt.");
        }
    }
    
    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); 
        }
    }
    
    public void writeFile(String filePath, List<String> lines) {
        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cancelBookingAndPayment(String bookingID) {
        updateBookingStatusInBookingFile(bookingID);
        updateBookingStatusInPaymentFile(bookingID);
    }
    
    public String generateBookingID() {
        Random random = new Random();
        int randomNum = 1000 + random.nextInt(9000);
        return "BK" + randomNum;
    }
}
