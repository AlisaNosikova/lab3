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
import java.util.HashMap;
import java.util.Vector;
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
    JTabbedPane tabPanel;

  
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
        JCheckBox countryB = new JCheckBox("По странам");
        JCheckBox regionB = new JCheckBox("По регионам");
        JCheckBox operatorB = new JCheckBox("По операторам");

    tabPanel = new JTabbedPane();
    ArrayList<JCheckBox> buttons = new ArrayList<>();
    buttons.add(countryB);
    buttons.add(regionB);
    buttons.add(operatorB);
    panel.add(countryB);
    panel.add(regionB);
    panel.add(operatorB);
     JOptionPane.showMessageDialog(null, panel, "Предупреждение", JOptionPane.PLAIN_MESSAGE);
    for (JCheckBox button: buttons){
        if (button.isSelected() == true){
            
         createTabs(button.getText());
        }
    }
      JOptionPane.showMessageDialog(null, tabPanel, "Предупреждение", JOptionPane.PLAIN_MESSAGE);
  

     
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
    public void createTabs(String text){
           JPanel page1 = new JPanel(); 
           page1.add(new JLabel(text)); 
           String header1 = new String();
           switch(text){
               case("По странам") -> header1 = "Страна";
               case("По регионам")-> header1 = "Регион";
               case("По операторам")-> header1 = "Оператор";
           }
           Vector<Vector<String>> data = new Vector<Vector<String>>();
        // Вектор с заголовками столбцов
          String[] headers = new String[] {header1,"Потребление","Год"};
           Vector<String> header = new Vector<String>();
          for (int i=0;i<headers.length;i++){
              header.add(headers[i]);
          }
        String[][] array = new String[][] {{ "Сахар" , "кг", "1.5" },
            { "Мука"  , "кг", "4.0" },
            { "Молоко", "л" , "2.2" }};
         for (int j = 0; j < array.length; j++) {
           
            Vector<String> row = new Vector<String>();
            for (int i = 0; i < array[j].length; i++) {
                row.add((String)array[j][i]);
            }
            data.add(row);
        }

       //// HashMap<String, HashMap<Integer, Double>> list = manager.getInfoConsumo();
       //    for (String key: list.keySet()) {
      //   for (Integer key1: list.get(key).keySet()){
       //     Vector<String> row = new Vector<String>();
       //     row.add(key);
       ////     row.add(key1.toString());
        //   row.add(list.get(key).get(key1).toString());
        //    data.add(row);
       // }}
           JTable table = new JTable(data, header);
           System.out.println(header);
          // Данные для таблиц
          page1.add(table);
          tabPanel.addTab("Tab 1", page1); 
           panel.add(tabPanel);
        // Простая таблица

           
        }

        
        
    }
    
