/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Registration extends AddUser {
    public Registration(String fn, String ln, String uid, String pw, String role, String sq, String ans){
        super(fn,ln,uid,pw,role,sq,ans); 
    }
    
    //Creating Override methods of CreateAcc from super calss AddUser
    
    @Override
    public void CreateAcc(){
        super.CreateAcc();
        JOptionPane.showMessageDialog(null, "Account successfully created.");
    }
}
