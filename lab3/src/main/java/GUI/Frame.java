/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;


/**
 *
 * @author User
 */
    public class Frame extends JFrame{
    public Frame(String title){
        super(title);
        setSize(350,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        Panel panel = new Panel(this);
        Container con= this.getContentPane();
        con.setLayout(new BorderLayout());
        con.add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
    
}
