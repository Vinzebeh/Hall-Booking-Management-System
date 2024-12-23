/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class History extends javax.swing.JFrame {

    private String UserID;
    private String UserName;
    /**
     * Creates new form History
     */
    public History(String UserID, String UserName) {
        this.UserID = UserID;
        this.UserName = UserName;
        initComponents();
        loadBookingData(UserID);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        HistoryTable = new javax.swing.JTable();
        CancelBtn = new javax.swing.JButton();
        DoneBtn = new javax.swing.JButton();
        ReceiptBtn = new javax.swing.JButton();
        FeedbackBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("History");

        HistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hall Type", "Hall Room", "Date", "Start Time", "End Time", "Booking ID", "Booking Date", "Booking Time", "Status"
            }
        ));
        jScrollPane1.setViewportView(HistoryTable);

        CancelBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        CancelBtn.setText("Cancel Booking");
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBtnActionPerformed(evt);
            }
        });

        DoneBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        DoneBtn.setText("Done");
        DoneBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoneBtnActionPerformed(evt);
            }
        });

        ReceiptBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ReceiptBtn.setText("View Receipt");
        ReceiptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReceiptBtnActionPerformed(evt);
            }
        });

        FeedbackBtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        FeedbackBtn.setText("Feedback");
        FeedbackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeedbackBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(193, 193, 193)))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ReceiptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelBtn)
                    .addComponent(FeedbackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DoneBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(ReceiptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(FeedbackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(DoneBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loadBookingData(String UserID) {
        String filePath = "Booking.txt";
        File file = new File(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            DefaultTableModel model = (DefaultTableModel) HistoryTable.getModel();
            while ((line = br.readLine()) != null) { 
                String[] dataRow = line.split(";");
                if (dataRow[0].equals(UserID)) {
                    String[] selectedData = new String[] {
                        dataRow[1],  
                        dataRow[2],  
                        dataRow[3],  
                        dataRow[4],  
                        dataRow[5],  
                        dataRow[7],  
                        dataRow[9], 
                        dataRow[10],
                        dataRow[11]
                    };
                    model.addRow(selectedData);
                }
            }     
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }
    
    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
    /*
        int selectedRow = HistoryTable.getSelectedRow();

    if (selectedRow != -1) {  
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to cancel this booking?", "Confirm Cancel", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) HistoryTable.getModel();
            String bookingID = model.getValueAt(selectedRow, 5).toString();
            System.out.println("Selected Booking ID: " + bookingID);
            
            CustomerClass obj = new CustomerClass();
            obj.cancelBookingAndPayment(bookingID);
            model.setValueAt("Canceled", selectedRow, 8);
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Please select a row to cancel the booking.");
    }
    */
    int selectedRow = HistoryTable.getSelectedRow();

        if (selectedRow != -1) {  
            DefaultTableModel model = (DefaultTableModel) HistoryTable.getModel();
            String bookingDateStr = model.getValueAt(selectedRow, 2).toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
            LocalDate bookingDate = LocalDate.parse(bookingDateStr, formatter); 
            LocalDate currentDate = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(currentDate, bookingDate);
            if (daysBetween < 0) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                        "This booking has already passed and cannot be canceled.");
            } 
            else if (daysBetween < 3) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                        "You cannot cancel this booking because it is less than 3 days before the booking date.");
            } else {
                int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
                        "Are you sure you want to cancel this booking?", "Confirm Cancel", javax.swing.JOptionPane.YES_NO_OPTION);

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    String bookingID = model.getValueAt(selectedRow, 5).toString();
                    System.out.println("Selected Booking ID: " + bookingID);

                    CustomerClass obj = new CustomerClass();
                    obj.cancelBookingAndPayment(bookingID);

                    model.setValueAt("Canceled", selectedRow, 8);
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a row to cancel the booking.");
        }
    }//GEN-LAST:event_CancelBtnActionPerformed

    private void DoneBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoneBtnActionPerformed
        this.dispose();
        Main mainPage = new Main(UserID,UserName); 
        mainPage.setVisible(true);
    }//GEN-LAST:event_DoneBtnActionPerformed

    private void ReceiptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReceiptBtnActionPerformed
        int selectedRow = HistoryTable.getSelectedRow(); 
        if (selectedRow != -1) {
            String bookingID = (String) HistoryTable.getValueAt(selectedRow, 5);
            Receipt receipt = new Receipt(bookingID, UserID, UserName, false); 
            receipt.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a booking to view receipt.");
        }
    }//GEN-LAST:event_ReceiptBtnActionPerformed

    private void FeedbackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FeedbackBtnActionPerformed
        int selectedRow = HistoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row first!Warning!");
            return; 
        }

        String bookingID = HistoryTable.getValueAt(selectedRow, 5).toString();
        String room = HistoryTable.getValueAt(selectedRow, 1).toString(); 

        String issue = JOptionPane.showInputDialog(this, "Please enter your feedback issue:");
        if (issue == null || issue.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Feedback issue cannot be empty!Will be Exit!");
            return; 
        }

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        String date = currentDate.format(dateFormatter);
        String schedulerName = ""; 
        String status = "";
        String feedbackData = String.join(";", bookingID, room, issue, date, schedulerName, status);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("customer service.txt", true))) {
            bw.write(feedbackData);
            bw.newLine();
            JOptionPane.showMessageDialog(this, "Feedback has been successfully submitted!Success!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while submitting feedback.Error.");
        }
    }//GEN-LAST:event_FeedbackBtnActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String UserID = "";
                String UserName = "";
                new History(UserID, UserName).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBtn;
    private javax.swing.JButton DoneBtn;
    private javax.swing.JButton FeedbackBtn;
    private javax.swing.JTable HistoryTable;
    private javax.swing.JButton ReceiptBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
