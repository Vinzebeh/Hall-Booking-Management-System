
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
 * @author PC
 */
public class AddUser {
    protected String Firstname;
    protected String Lastname;
    protected String UserID;
    protected String UserPw;
    protected String ConfirmPw;
    protected String UserRole;
    protected String SecQues;
    protected String Answer;
    
    public AddUser(){}
    
    public AddUser(String fn, String ln, String uid, String pw, String role, String sq, String ans){
        this.Firstname = fn;
        this.Lastname = ln;
        this.UserID = uid;
        this.UserPw = pw;
        this.UserRole = role;
        this.SecQues = sq;
        this.Answer = ans;
    }
    
    public boolean Checkuid(String uid){
        boolean status = true;
        try{            
            FileReader fr = new FileReader("users.txt");
                BufferedReader br = new BufferedReader(fr);
                String read;
                while((read = br.readLine()) != null){
                    if(read.split(";").length >= 2 && read.split(";")[2].equals(uid)){
                        status = false;
                        break;
                    }
                }
                br.close();
        }catch (IOException e){
            System.out.println("Exception Error:"+e.getMessage());
        }
        return status;
    }
    
    public boolean Checkpw(String cpw, String pw){
        boolean status = true;
        if(!cpw.equals(pw)){
                status = false;
        }
        return status;
    }
    
    public void CreateAcc(){
        try{
            //Firstname Lastname;User ID(email);Password;User Role;Security Question(index);answer
            ArrayList <String> userinfo = new ArrayList<>();
            userinfo.add(Firstname);
            userinfo.add(Lastname);
            userinfo.add(UserID);
            userinfo.add(UserPw);
            userinfo.add(UserRole);
            userinfo.add(SecQues);
            userinfo.add(Answer);

            FileWriter fw = new FileWriter("users.txt", true);
            fw.write(userinfo.get(0)+";"+userinfo.get(1)+";"+userinfo.get(2)+";"+userinfo.get(3)+";"+userinfo.get(4)+";"+userinfo.get(5)+";"+userinfo.get(6)+"; "+"\n");

            fw.close();
            JOptionPane.showMessageDialog(null,"Successfully added account.");
            userinfo.clear();
        }catch (IOException e){
            System.out.println("Exception Error:"+e.getMessage());
        }
    }
    
}
