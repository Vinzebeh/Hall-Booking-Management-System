
import java.io.BufferedReader;
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
 * @author PC
 */
public class ViewProfile {
    
    private String UserId;
    
    public ViewProfile() {}
    
    public ViewProfile(String uid){
        this.UserId = uid;
    }
    
    public ArrayList ReadProfile(String uid){
        ArrayList<String> userinfo = new ArrayList<>();
        try{
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;
            
            while((read = br.readLine()) != null){
                if(read.split(";")[2].equals(uid)){
                    userinfo.add(read);
                }
            }
            
        }catch(IOException e) {
            System.out.println("Exception Error:"+e.getMessage());
        }
        return userinfo;
    }
    
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
    }
}
