/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Readers.Manager;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeCellRenderer;

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
  
    public Panel(JFrame frame){
       this.panel = this;
       this.frame = frame;
       this.manager = new Manager();
       this.showButton = new JButton("ShowTree");
       this.importButton = new JButton("Import");
       this.exitButton = new JButton("Exit");
       setLayout(new GridBagLayout());
       addButtons();
       importButton.addActionListener(new ImportActionListener());
       showButton.addActionListener(new ShowActionListener());
       exitButton.addActionListener(new ExitActionListener());
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
            manager.startChain(selectedFile);
             //JOptionPane.showMessageDialog(Panel.this, fileChooser.getSelectedFile().getName());
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
      public void addButtons(){
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
    }
       public class ExitActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          frame.dispose();
        }

        
        
    }
    
}