/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chain;

import GUI.Panel;
import Readers.Reactor;
import Readers.Reader;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class BaseHandler implements Handler{
    Handler nextHandler;
    Reader reader;
    private ArrayList<Reactor> list = new ArrayList<>();
    public BaseHandler(Reader reader){
        this.reader = reader;
    }
    @Override
    public void setNext(Handler next) {
        nextHandler = next;
    }

    @Override
     public ArrayList<Reactor> handle(File file) {
       
       if (file.getName().matches( ".*\\." + reader.getFileType())){          
           for (Reactor reactor: reader.Read(file)){
               reactor.setSource(reader.getFileType());
               list.add(reactor);
           }
       }
       else if(nextHandler!=null){
           return nextHandler.handle(file);
       }
       else{
             JOptionPane.showMessageDialog(null, "Нет такого типа", "Предупреждение", JOptionPane.ERROR_MESSAGE);
       }
        return list;
    }

}
