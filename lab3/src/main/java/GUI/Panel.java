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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;
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
    private JButton connectToServer;
    JTabbedPane tabPanel;

  
    public Panel(JFrame frame){
       this.panel = this;
       this.frame = frame;
       this.manager = new Manager();
       this.createDB = new JButton("Создать базу данных");
       this.deleteDB = new JButton("Удалить базу данных");
       this.calculateButton= new JButton("Произвести расчеты");
       this.showButton = new JButton("Показать дерево реакторов");
       this.importButton = new JButton("Подгрузить доп.информацию");
       this.exitButton = new JButton("Выйти");
    
this.connectToServer = new JButton("Подключиться к серверу");


       
       setLayout(new GridBagLayout());
       addButtons();
       importButton.addActionListener(new ImportActionListener());
       showButton.addActionListener(new ShowActionListener());
       exitButton.addActionListener(new ExitActionListener());
       createDB.addActionListener(new  createDBActionListener());
       deleteDB.addActionListener(new deleteDBActionListener());
       calculateButton.addActionListener(new calculateActionListener());
       connectToServer.addActionListener(new ConnectActionListener());
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
     //JOptionPane.showMessageDialog(null, panel, "Предупреждение", JOptionPane.PLAIN_MESSAGE);
         
       if(manager.getInfo()==null){
                    JOptionPane.showMessageDialog(null, "Пожалуйста, выберите файл для подгрузки данных о реакторах", "Предупреждение", JOptionPane.ERROR_MESSAGE);
             }
       else{
            try {
                if (manager.getLoadingStatus() == false){
                   JPanel panel1 = new JPanel(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

// Добавление текста в центр панели
JLabel message = new JLabel("Выгрузка данных началась. Нажмите для подтверждения", SwingConstants.CENTER);
gbc.gridx = 0;
gbc.gridy = 0;
gbc.insets = new Insets(10, 10, 10, 10);
panel1.add(message, gbc);

// Добавление гифки внизу панели
ImageIcon gifIcon = new ImageIcon("C:\\Users\\User\\Documents\\GitHub\\lab3\\lab3\\src\\main\\resources\\loading.gif");
JLabel gifLabel = new JLabel(gifIcon);
gbc.gridy = 1;
gbc.insets = new Insets(0, 0, 10, 0);
panel1.add(gifLabel, gbc);
                  

// Отображение диалогового окна
JOptionPane.showMessageDialog(null, panel1, null, JOptionPane.PLAIN_MESSAGE);
 
                     manager.loadInfo();
                    JOptionPane.showMessageDialog(null, "Выгрузка данных завершена", "Предупреждение", JOptionPane.PLAIN_MESSAGE);
                   
                }
             JOptionPane.showMessageDialog(null, panel, "Выберите тип калькуляции", JOptionPane.QUESTION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
    for (JCheckBox button: buttons){
        if (button.isSelected() == true){
         createTabs(button.getText());
        }
    }
            }
      JOptionPane.showMessageDialog(null, tabPanel, "Результаты", JOptionPane.PLAIN_MESSAGE);

    
     }
     }
    public class createDBActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { 
    try {
    Connection con = manager.getConnection();
    DatabaseMetaData metaData = con.getMetaData();
    ResultSet tables = metaData.getTables(null, "public", "%", new String[] {"TABLE"});
    int tablesCount=0;
    while (tables.next()) {
        tablesCount+=1;
        System.out.println(tablesCount);
    }
    if (tablesCount==0){
        manager.createDB();
        JOptionPane.showMessageDialog(null, "Таблицы созданы и заполнены", null, JOptionPane.INFORMATION_MESSAGE);

    }
    else{
        JOptionPane.showMessageDialog(null, "База данных уже заполена", null, JOptionPane.INFORMATION_MESSAGE);
    }  
} 
 
    catch (NullPointerException exx){
               JOptionPane.showMessageDialog(null, "Подключения к серверу не найдено", "Предупреждение", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException | IOException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
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
                   JOptionPane.showMessageDialog(null, "Нет подключения к базе данных", "Предупреждение", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null, "Невозможно очистить базу данных, так как она не создана", "Предупреждение", JOptionPane.ERROR_MESSAGE);
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
             manager.useChain(selectedFile);
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
        gbc.insets = new Insets(10, 20, 20, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        importButton.setBackground(Color.WHITE);
        add(importButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        showButton.setBackground(Color.WHITE);
        add(showButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        ImageIcon icon = new ImageIcon("C:\\Users\\User\\Documents\\GitHub\\lab3\\lab3\\src\\main\\resources\\icon1.png");
Image img = icon.getImage();
Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
icon = new ImageIcon(newImg);

connectToServer.setIcon(icon);
        // connectToServer.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
         connectToServer.setBackground(Color.WHITE);
        add(connectToServer, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        createDB.setBackground(Color.WHITE);
        add(createDB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        deleteDB.setBackground(Color.WHITE);
        add(deleteDB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        calculateButton.setBackground(Color.WHITE);
        add(calculateButton, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        exitButton.setBackground(Color.WHITE);
        add(exitButton, gbc);
        
    }

       public class ExitActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          frame.dispose();
            try {
                manager.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       }
    public class ConnectActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                manager.connectToDB();
                if (manager.getConnection()!=null){
                     JOptionPane.showMessageDialog(null, "Успешное подключение к базе данных", null, JOptionPane.INFORMATION_MESSAGE);
                }
                 Statement stmt = manager.getConnection().createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM " + "regions");
    ResultSetMetaData rsmd = rs.getMetaData();
    StringJoiner columnNames = new StringJoiner(",");
    System.out.println(columnNames);
    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        columnNames.add(rsmd.getColumnName(i));
        System.out.println(rsmd.getColumnName(i));
    }
    
    String insertQuery = "INSERT INTO " + "regions" + " (" + columnNames.toString() + ") VALUES (" + "?,".repeat(rsmd.getColumnCount() - 1) + "?)";
 System.out.println(insertQuery);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Не удалось подключиться к серверу", null, JOptionPane.ERROR_MESSAGE);
            }
        }
       }
    public void createTabs(String text){
          
    JPanel page1 = new JPanel();
    page1.add(new JLabel(text));
    String header1 = switch (text) {
        case "По странам" -> "Страна";
        case "По регионам" -> "Регион";
        case "По операторам" -> "Оператор";
        default -> "";
    };

    Object[] columnsHeader = new String[] {header1, "Потребление", "Год"};
    // Создание заголовков столбцов
    Vector<String> columnNames = new Vector<>();
    for (Object col : columnsHeader) {
        columnNames.add((String)col);
    }

    // Создание данных таблицы
    Vector<Vector<String>> data = new Vector<>();
    
    HashMap<String, HashMap<Integer, Double>> list = manager.getInfoConsump(text);
    if (list==null){
        manager.calculate(text); 
         list = manager.getInfoConsump(text);
    }
    for (String key: list.keySet()) {
        for (Integer key1: list.get(key).keySet()){
         Vector<String> row = new Vector<String>();
         row.add(key);
         row.add(key1.toString());
         Double result = list.get(key).get(key1);
         if (result!=0){
         row.add(result.toString());
          data.add(row);
        }}
    }
    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
    page1.add(scrollPane);
    tabPanel.addTab(text,page1);
}


           
        }



    
   
