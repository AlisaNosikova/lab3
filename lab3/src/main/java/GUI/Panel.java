/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Readers.Manager;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
    private ButtonGroup buttonGroup;
    private Panel panel;
  
    public Panel(JFrame frame){
       this.panel = this;
       this.frame = frame;
       this.manager = new Manager();
       this.showButton = new JButton("ShowTree");
       this.importButton = new JButton("Import");
       this.exitButton = new JButton("Exit");
       this.buttonGroup = new ButtonGroup();
       setLayout(new GridBagLayout());
       addButtons();
       importButton.addActionListener(new ImportActionListener());
       showButton.addActionListener(new ShowActionListener());
       exitButton.addActionListener(new ExitActionListener());
    }
    public class ImportActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
    boolean choiceIsMade = false;
    JFileChooser fileChooser = new JFileChooser("src\\main\\resources");
    fileChooser.setDialogTitle("Выбор директории");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int result = fileChooser.showOpenDialog(Panel.this);
    File selectedFile = fileChooser.getSelectedFile();

    if (selectedFile != null && selectedFile.exists()) {
        if (result == JFileChooser.APPROVE_OPTION) {
            manager.startChain(selectedFile);
            JOptionPane.showMessageDialog(Panel.this, fileChooser.getSelectedFile());
        }
    } else {
        if (selectedFile != null && !selectedFile.exists()) {
            JOptionPane.showMessageDialog(null, "Файл не существует", "Предупреждение", JOptionPane.ERROR_MESSAGE);
        }
    }

    }
    }
    public class ShowActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
              MyTreeModel model = new MyTreeModel();
              model.setReactorsList(manager.getInfo());
              JTree tree = new JTree(model);
            
               DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
               ImageIcon icon1 = new ImageIcon("C:\\Users\\User\\Downloads\\images\\reactor.png");
              ImageIcon resizedIcon1 = new ImageIcon(icon1.getImage().getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH));
              renderer.setOpenIcon(resizedIcon1);
               ImageIcon icon2 = new ImageIcon("C:\\Users\\User\\Downloads\\images\\light.png");
              ImageIcon resizedIcon2 = new ImageIcon(icon2.getImage().getScaledInstance(21, 21, java.awt.Image.SCALE_SMOOTH));
              renderer.setLeafIcon(resizedIcon2);
      //  renderer.setOpenIcon  (new ImageIcon("images/woman.png"));
       // renderer.setClosedIcon(new ImageIcon("images/star.png"));
        // Определение в дереве отображающего объекта
        tree.setCellRenderer(renderer);
                  JScrollPane scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
              panel.removeAll(); // Удалить все существующие компоненты
              panel.setLayout(new BorderLayout());
              panel.add(scrollPane, BorderLayout.CENTER);
              revalidate();
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