
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
public class Authentication {
    private String username;
    private String email;
    private String password;
    private String role;
    
    public Authentication(){}
    
    public Authentication(String uid){
        this.email = uid;
    }
    
    public Authentication(String e, String pw){
        this.email = e;
        this.password = pw;
    }

    public boolean login(){
        boolean status = false;
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder updatedContent = new StringBuilder();
            String read;
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currentTime = dtf.format(LocalDateTime.now());
            
            while((read = br.readLine()) != null){
                //username;email;password;userrole
                //System.out.println(read);
                String[] userDetails = read.split(";");
                if (userDetails[2].equals(email) && userDetails[3].equals(password)){
                    status = true;
                    this.username = userDetails[0];
                    
                    userDetails[7] = currentTime;
                    
                    switch(userDetails[4]){
                        case "A" -> {
                            System.out.println("Hi, Admin "+username);
                            role = "Admin";
                            MainPageForm admin = new MainPageForm(username, role, email);
                            admin.setVisible(true);
                            //Admin(username);
                            break;
                        }
                        case "S" -> {
                            System.out.println("Hi, Scheduler "+username);
                            role = "Scheduler";
                            Scheduler scheduler = new Scheduler(email, role);
                            scheduler.setVisible(true);
                            //Scheduler(username);
                            break;
                        }
                        case "M" -> {
                            System.out.println("Hi, Manager "+username);
                            role = "Manager";
                            ManagerMainForm manager = new ManagerMainForm(username, role, email);
                            manager.setVisible(true);
                            //Manager(username);
                            break;
                        }
                        case "C" -> {
                            System.out.println("Hi, Customer "+username);
                            role = "Customer";
                            Main customer = new Main(email, username);
                            customer.setVisible(true);
                            AdvertisementPage adver = new AdvertisementPage();
                            adver.setVisible(true);
                            //Customer(username);
                            break;
                        }
                        default -> {
                            JOptionPane.showMessageDialog(null,"Account may have issues, please find admin.");
                            status = false;
                        }
                    }
                }
                
                updatedContent.append(String.join(";", userDetails)).append("\n");
            }
            br.close();
            
            if (status == true){
                FileWriter fw = new FileWriter("users.txt");
                fw.write(updatedContent.toString());
                fw.close();
                
                JOptionPane.showMessageDialog(null, "Successfully login.");
            }else {
                JOptionPane.showMessageDialog(null, "Invalid login.Please Try Again.");
            }
            
        } catch(IOException e) {
            System.out.println("Exception Error:"+e.getMessage());
        }
        return status;
    }
    
    public boolean UidValidity(){        
        boolean status = false;
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder updatedContent = new StringBuilder();
            String read;
            
            while((read = br.readLine()) != null){
                String[] userDetails = read.split(";");
                if(userDetails[2].equals(email)){
                    status = true;
                }
            }
            
        }catch(IOException e) {
            System.out.println("Exception Error:"+e.getMessage());
        }
        return status;
    }
    
    public ArrayList RetrievePassword(){
        ArrayList<String> list = new ArrayList<>();
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;
            
            while((read = br.readLine()) != null){
                String[] userDetails = read.split(";");
                if(userDetails[2].equals(email)){
                    String sqno = userDetails[5];
                    String ques;
                    switch(sqno){
                        case "1" : 
                            ques = "What is your favourite color?";
                            break;
                        case "2" :
                            ques = "Which university did you attend?";
                            break;
                        case "3" :
                            ques =  "What is your main hobby?";
                            break;
                        case "4" :
                            ques = "Who is your favourite athlete?";
                            break;
                        case "5" :
                            ques = "Who was your favourite secondary school teacher?";
                            break;
                        case "6" :                               
                            ques = "What is your pet's name?";
                            break;
                        default:
                            ques = "Security question not selected";                        
                    }
                    list.add(ques);
                    list.add(userDetails[6]);
                    list.add(userDetails[3]);                    
                }
            }
            br.close();
        }catch(IOException e) {
            System.out.println("Exception Error:"+e.getMessage());
        }
        return list;
    }
    
    /*
    --Select One--
What is your favourite color?
Which university did you attemd?
What is your main hobby?
Who is your favourite athlete?
Who was your favourite secondary school teacher?
What is your pet's name?
    */
}
