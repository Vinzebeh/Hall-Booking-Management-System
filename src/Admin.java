
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
public class Admin {
    private String UserId;
    
    public Admin(){}
    
    public Admin(String uid){
        this.UserId = uid;
    }
    
    public boolean DeleteAcc(){
        boolean status = false;
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder updatedcontent = new StringBuilder();
            String read;           
            while ((read = br.readLine()) != null) {
                if (!read.split(";")[2].equals(UserId)) { //check if the uid matches or not, if match then skip the row
                    updatedcontent.append(read).append(" \n"); //append the others acc into stringbuilder
                }
            }
            br.close();
            
            //Writing back the others acc from stringbuilder into txt file
            FileWriter fw = new FileWriter("users.txt");
            fw.write(updatedcontent.toString());
            fw.close();
            status = true;          
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return status;
    }
    
    public ArrayList SearchRole (String role){
        ArrayList<String> scrl = new ArrayList<>();
        try {
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;
            
            while ((read = br.readLine()) != null) {
                String[] userinfo = read.split(";");
                if (userinfo[4].equals(role)) { 
                    scrl.add(read);
                } 
            }
            br.close();
            
        } catch (IOException e) {
            System.out.println("Exception:" + e.getMessage());
        }
        return scrl;
    }
    
    public ArrayList SearchAcc(String search){
        ArrayList<String> sc = new ArrayList<>();
        try {
            boolean found = false;
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;
            
            while ((read = br.readLine()) != null) {
                String[] userinfo = read.split(";");
                if (userinfo[0].equals(search)
                        || userinfo[1].equals(search)
                        || userinfo[2].equals(search)) { 
                    sc.add(read);
                    found = true;
                } 
            }
            br.close();
            if (!found) {
                JOptionPane.showMessageDialog(null, search + " not found");
            }
        } catch (IOException e) {
            System.out.println("Exception:" + e.getMessage());
        }
        return sc;
    }
    
    public ArrayList ReadStaff(){
        ArrayList<String> rd = new ArrayList<>();
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String read;           
            while ((read = br.readLine()) != null) {
                if (read.split(";")[4].equals("S") || read.split(";")[4].equals("M")) {
                    rd.add(read);
                }
            }
            br.close();      
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        Collections.sort(rd, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String role1 = o1.split(";")[4]; // Role of the first user
                String role2 = o2.split(";")[4]; // Role of the second user
                return role1.compareTo(role2); // Compare roles alphabetically
            }
        });
        return rd;
        
    }
    
    public ArrayList ReadUser(){
        ArrayList<String> rd = new ArrayList<>();
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String read;           
            while ((read = br.readLine()) != null) {
                if (read.split(";")[4].equals("C")) {
                    rd.add(read);
                }
            }
            br.close();      
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        Collections.sort(rd, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String name1 = o1.split(";")[0]; // First name of the first user
                String name2 = o2.split(";")[0]; // First name of the second user
                return name1.compareTo(name2); // Compare roles alphabetically
            }
        });
        return rd;
    }
    
    public int[] CountAcc(){
        int scheduler = 0;
        int manager = 0;
        int customer = 0;
        
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String read;           
            while ((read = br.readLine()) != null) {
                if (read.split(";")[4].equals("S")) {
                    scheduler++;
                }else if(read.split(";")[4].equals("M")){
                    manager++;
                }else if(read.split(";")[4].equals("C")){
                    customer++;
                }
            }
            br.close();      
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
        return new int[]{scheduler, manager, customer};
    }
    
    public ArrayList ReadAll(){
         ArrayList<String> rd = new ArrayList<>();
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String read;           
            while ((read = br.readLine()) != null) {
                if(!read.split(";")[4].equals("A")){
                    rd.add(read);
                }
            }
            br.close();      
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        Collections.sort(rd, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String role1 = o1.split(";")[4]; // Role of the first user
                String role2 = o2.split(";")[4]; // Role of the second user
                return role1.compareTo(role2); // Compare roles alphabetically
            }
        });
        
        return rd;
    }
    
    public boolean SuspendAcc(){
        boolean status = false;
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder updatedcontent = new StringBuilder();
            String read;           
            while ((read = br.readLine()) != null) {
                String[] userDetails = read.split(";");
                if (userDetails[2].equals(UserId) && !userDetails[4].contains("(B)")) { //check if the uid matches or not, if match then skip the row
                    userDetails[4] = userDetails[4]+"(B)";
                    read = String.join(";", userDetails);
                    status = true; 
                }
                
                updatedcontent.append(read).append("\n");
                
            }
            br.close();
            
            //Writing back the others acc from stringbuilder into txt file
            FileWriter fw = new FileWriter("users.txt");
            fw.write(updatedcontent.toString());
            fw.close();
                     
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return status;
    }
    
    public boolean ActivateAcc(){
        boolean status = false;
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder updatedcontent = new StringBuilder();
            String read;           
            while ((read = br.readLine()) != null) {
                String[] userDetails = read.split(";");
                if (userDetails[2].equals(UserId) && userDetails[4].contains("(B)")) { //check if the uid matches or not, if match then skip the row
                    userDetails[4] = userDetails[4].replace("(B)","");
                    read = String.join(";", userDetails);
                    status = true; 
                }
                updatedcontent.append(read).append("\n");
            }
            br.close();
            
            //Writing back the others acc from stringbuilder into txt file
            FileWriter fw = new FileWriter("users.txt");
            fw.write(updatedcontent.toString());
            fw.close();
                     
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return status;
    }
    
    public ArrayList ReadBookings(){
        ArrayList<String> bookinginfo = new ArrayList<>();
        try{
            FileReader fr = new FileReader("Booking.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;
            
            while((read = br.readLine()) != null){
                if(read.split(";")[11].equals("Proceed")){
                    bookinginfo.add(read);
                }
            }
            
        }catch(IOException e) {
            System.out.println("Exception Error:"+e.getMessage());
        }
        return bookinginfo;
    }
    
    public ArrayList ReadHallType(String type){
        ArrayList<String> bookinginfo = new ArrayList<>();
        try{
            FileReader fr = new FileReader("Booking.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;
            
            while((read = br.readLine()) != null){
                if(read.split(";")[1].equals(type) && read.split(";")[11].equals("Proceed")){
                    bookinginfo.add(read);
                }
            }
            
        }catch(IOException e) {
            System.out.println("Exception Error:"+e.getMessage());
        }
        return bookinginfo;
    }

    public ArrayList CheckUpcoming(){
        ArrayList<String> upcomingbooking = new ArrayList<>();
        try {
        FileReader fr = new FileReader("Booking.txt");
        BufferedReader br = new BufferedReader(fr);
        String read;
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/M/d");
        LocalDate today = LocalDate.now(); //get local time

        while ((read = br.readLine()) != null) {
            String[] bookingDetails = read.split(";");
            LocalDate bookingDate = LocalDate.parse(bookingDetails[3], dateformat);// convert string to date with format 

            if (bookingDate.isAfter(today) && bookingDetails[11].equals("Proceed")) {// compare booking date and today
                upcomingbooking.add(read);            
            }
        }

        br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading bookings: " + e.getMessage());
        }
        return upcomingbooking;
    }
    
    public ArrayList CheckPast(){
        ArrayList<String> pastbooking = new ArrayList<>();
        try {
        FileReader fr = new FileReader("Booking.txt");
        BufferedReader br = new BufferedReader(fr);
        String read;
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/M/d");
        LocalDate today = LocalDate.now();

        while ((read = br.readLine()) != null) {
            String[] bookingDetails = read.split(";");
            LocalDate bookingDate = LocalDate.parse(bookingDetails[3], dateformat);// convert string to date with format 

            if (bookingDate.isBefore(today) && bookingDetails[11].equals("Proceed")) {// compare booking date and today
                pastbooking.add(read);            
            }
        }

        br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading bookings: " + e.getMessage());
        }
        return pastbooking;
    }
    
    public ArrayList SearchBooking(String search){
        ArrayList<String> sc = new ArrayList<>();
        try {
            boolean found = false;
            FileReader fr = new FileReader("Booking.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;
            
            while ((read = br.readLine()) != null) {
                String[] bookinginfo = read.split(";");
                if (bookinginfo[0].equals(search)
                        || bookinginfo[1].equals(search)
                        || bookinginfo[2].equals(search)
                        || bookinginfo[7].equals(search)) { 
                    sc.add(read);
                    found = true;
                } 
            }
            br.close();
            if (!found) {
                JOptionPane.showMessageDialog(null, search + " not found");
            }
        } catch (IOException e) {
            System.out.println("Exception:" + e.getMessage());
        }
        return sc;
    }
    /*
    public boolean UpdateProfile(ArrayList<String> info){
        boolean status = false;
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder updatedContent = new StringBuilder();
            String read;
            
            while((read = br.readLine()) != null){
                String[] userinfo = read.split(";");
                if(userinfo[2].equals(UserId)){
                    userinfo[0] = info.get(0); // firstname
                    userinfo[1] = info.get(1); // lastname
                    userinfo[3] = info.get(2); // password
                    userinfo[5] = info.get(3); // question
                    userinfo[6] = info.get(4); // answer
                    read = String.join(";", userinfo);
                    status = true;
                }
                updatedContent.append(read).append("\n");
            }
            br.close();
            
            FileWriter fw = new FileWriter("users.txt");
            fw.write(updatedContent.toString());
            fw.close();
            
        }catch(IOException e) {
            System.out.println("Exception Error:"+e.getMessage());
        }
        return status;
    }*/
}
