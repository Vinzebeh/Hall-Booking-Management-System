
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ynning
 */
public class DeleteHall {
    
    private static final String HALL_BOOKING_FILE = "hall_booking.txt";
    private static final String HALL_INFO_FILE = "hall_info.txt";
    private JTable hallTable;
    
    public DeleteHall(JTable hallTable) {
        this.hallTable = hallTable;
    }

    // Method to delete a hall based on type and name
    public void deleteHall(String hallType, String hallName) {
        try {
            deleteEntryFromFile(HALL_BOOKING_FILE, hallType, hallName);
            deleteEntryFromFile(HALL_INFO_FILE, hallType, hallName);

            // Removed the success message here

            updateTableModel();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error deleting hall: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void deleteEntryFromFile(String filePath, String hallType, String hallName) throws IOException {
        File file = new File(filePath);
        List<String> updatedEntries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(";");
                if (details.length < 2) {
                    // Handle malformed line if necessary
                    continue;
                }

                String currentHallType = details[0];
                String currentHallName = details[1];

                if (!(currentHallType.equals(hallType) && currentHallName.equals(hallName))) {
                    updatedEntries.add(line);
                }
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (String entry : updatedEntries) {
                bw.write(entry);
                bw.newLine();
            }
        }
    }

    private void updateTableModel() {
        DefaultTableModel tableModel = (DefaultTableModel) hallTable.getModel();
        tableModel.setRowCount(0); // Clear existing rows

        // Debug: Print if the table model is correct
        System.out.println("Table Model Row Count: " + tableModel.getRowCount());

        // Load updated data from hall_info.txt
        try (BufferedReader br = new BufferedReader(new FileReader(HALL_INFO_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Line read from file: " + line); // Debug: print each line from the file

                String[] hallDetails = line.split(";");
                if (hallDetails.length >= 4) { // Ensure there are at least 4 columns
                    tableModel.addRow(new Object[]{hallDetails[0], hallDetails[1], hallDetails[2], hallDetails[3]});
                } else {
                    System.out.println("Skipping line due to insufficient data: " + Arrays.toString(hallDetails));
                }
            }
            tableModel.fireTableDataChanged();
            hallTable.revalidate();
            hallTable.repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading table data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
