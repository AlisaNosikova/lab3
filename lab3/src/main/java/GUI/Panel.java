/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Readers.Manager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class Panel extends JPanel{
    private Manager manager;
    private JButton showButton;
    private JButton importButton;
    private JButton exitButton;
    private JFrame frame;
    private Panel panel;
    private JButton createDB;
    private JButton deleteDB;
    private JButton calculateButton;
  
    public Panel(JFrame frame){
       this.panel = this;
       this.frame = frame;
       this.manager = new Manager();
       this.createDB = new JButton("Создать базу данных");
       this.deleteDB = new JButton("Удалить базу данных");
       this.calculateButton= new JButton("Произвести расчеты");
       this.showButton = new JButton("ShowTree");
       this.importButton = new JButton("Import");
       this.exitButton = new JButton("Exit");
       setLayout(new GridBagLayout());
       addButtons();
       importButton.addActionListener(new ImportActionListener());
       showButton.addActionListener(new ShowActionListener());
       exitButton.addActionListener(new ExitActionListener());
       createDB.addActionListener(new  createDBActionListener());
       deleteDB.addActionListener(new deleteDBActionListener());
       calculateButton.addActionListener(new calculateActionListener());
    }
     public class calculateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        JPanel panel = new JPanel();
        JRadioButton countryB = new JRadioButton("По странам");
        JRadioButton regionB = new JRadioButton("По регионам");
        JRadioButton operatorB = new JRadioButton("По операторам");

    
    ArrayList<JRadioButton> buttons = new ArrayList<>();
    buttons.add(countryB);
    buttons.add(regionB);
    buttons.add(operatorB);
    panel.add(countryB);
    panel.add(regionB);
    panel.add(operatorB);
     JOptionPane.showMessageDialog(null, panel, "Предупреждение", JOptionPane.PLAIN_MESSAGE);
    for (JRadioButton button: buttons){
        System.out.println(button.getText());
     //   manager.calculator(button.getName());
    }
            

        
        
    }
    }
    public class createDBActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Connection con = manager.getConnection();
            try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM information_schema.tables WHERE table_type = 'BASE TABLE'");
            rs.next(); 
            int tableCount = rs.getInt(1);
            if (tableCount == 0) {
                manager.createDB();
                manager.loadInfo();
            } else {
               JOptionPane.showMessageDialog(null, "База даннных уже создана и заполнена", "Предупреждение", JOptionPane.ERROR_MESSAGE);
          
            }
            
        }
           catch (SQLException | IOException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(NullPointerException ex){
                   JOptionPane.showMessageDialog(null, "Не удалось подключиться к базе данных", "Предупреждение", JOptionPane.ERROR_MESSAGE);
            }

        
        
    }
    }
      public class deleteDBActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
           
    JPanel panel = new JPanel();
    JRadioButton yesB = new JRadioButton("Да");
    JRadioButton noB = new JRadioButton("Нет");
    ButtonGroup group = new ButtonGroup();
    group.add(yesB);
    group.add(noB);
    panel.add(yesB);
    panel.add(noB);
    JOptionPane.showMessageDialog(null, panel, "Вы уверены, что хотите удалить базу данных?", JOptionPane.QUESTION_MESSAGE);

                if (yesB.isSelected() == true){
                manager.deleteDB();
                }
            
            }   catch (NullPointerException ex) {
                   JOptionPane.showMessageDialog(null, "Невозможно удалить базу данных, так как она не создана", "Предупреждение", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        
        
    }
    }
    public class ImportActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
    
    JFileChooser fileChooser = new JFileChooser("src\\main\\resources");
    fileChooser.setDialogTitle("Выбор директории");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int result = fileChooser.showOpenDialog(Panel.this);
    File selectedFile = fileChooser.getSelectedFile();
    JOptionPane.showMessageDialog(Panel.this, fileChooser.getSelectedFile().getName());
    if (selectedFile != null && selectedFile.exists()) {
        try{
        if (result == JFileChooser.APPROVE_OPTION) {
            try{       
             JOptionPane.showMessageDialog(Panel.this, fileChooser.getSelectedFile().getName());
           }  catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Для этого файла нет обработчика", "Предупреждение", JOptionPane.ERROR_MESSAGE);
                }
           
        }
     else {
        if (selectedFile != null && !selectedFile.exists()) {
            JOptionPane.showMessageDialog(null, "Файл не существует", "Предупреждение", JOptionPane.ERROR_MESSAGE);
        }
      
    }
  }catch (NullPointerException en){
              JOptionPane.showMessageDialog(null, "Для этого файла нет обработчика", "Предупреждение", JOptionPane.ERROR_MESSAGE);
           }
   
    }
    }
    }
    public class ShowActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            MyTreeModel model = new MyTreeModel();
            if (manager.getInfo().isEmpty()){
              JOptionPane.showMessageDialog(null, "Нет данных для построение дерева", "Предупреждение", JOptionPane.ERROR_MESSAGE);
            }
            else{
            model.addReactorsList(manager.getInfo());
            JTree tree = new JTree(model);
            JScrollPane scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel.removeAll(); // Удалить все существующие компоненты
            panel.setLayout(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            revalidate();
            }
    }catch (NullPointerException en){
        JOptionPane.showMessageDialog(null, "Нет данных для построение дерева", "Предупреждение", JOptionPane.ERROR_MESSAGE);
    }
    }
    }
      private void addButtons(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(importButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(showButton, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(exitButton, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(createDB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(deleteDB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(calculateButton, gbc);
        
    }
       public class ExitActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          frame.dispose();
        }

        
        
    }
    
}