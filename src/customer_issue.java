
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
public class customer_issue {
    private static final String ISSUE_FILE = "customer_issue.txt";
    private List<String[]> issueData;

    public customer_issue() {
        issueData = new ArrayList<>();
    }

    public void loadIssueData() {
        // Load the data from the "customer_issue.txt" file
        issueData.clear(); // Clear the existing data
        try (BufferedReader br = new BufferedReader(new FileReader(ISSUE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(";"); // Assuming the delimiter is ';'
                issueData.add(details);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
    }

    public void saveIssueData(int selectedRow, String newStatus) {
        // Update the status in the issueData list
        issueData.get(selectedRow)[6] = newStatus;

        // Save the updated data back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ISSUE_FILE))) {
            for (String[] row : issueData) {
                bw.write(String.join(";", row));  // Write each row back to the file
                bw.newLine();                     // Add a new line for each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayTableData(JTable jTable) {
        // Define the table model with column names "Task ID", "Issue", "Status"
        DefaultTableModel model = new DefaultTableModel(new String[] {"Task ID", "Issue", "Status"}, 0);
        
        // Populate the table with the relevant columns (Task ID, Issue, Status)
        for (String[] row : issueData) {
            model.addRow(new Object[] {row[0], row[2], row[6]}); // Task ID is index 0, Issue is index 2, Status is index 6
        }

        // Set the table model to display the data
        jTable.setModel(model);
    }

    public List<String[]> getIssueData() {
        return issueData;
    }
}
