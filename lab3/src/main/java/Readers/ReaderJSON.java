/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class ReaderJSON implements Reader{
    private ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Reactor> reactorsJSON = new ArrayList<>();
    @Override
    public ArrayList<Reactor> Read(File file){
   
     try{
       List<Reactor> listReactors = mapper.readValue(file, new TypeReference<List<Reactor>>(){});
        for (Reactor reactor: listReactors){
     
           reactorsJSON.add(reactor);
       } 
     }catch(MismatchedInputException ex){
                  JOptionPane.showMessageDialog(null, "Файл невозмозжно прочитать", "Предупреждение", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
            Logger.getLogger(ReaderJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return  reactorsJSON;
        
    }
    @Override
    public String getFileType(){
        
        return "json";
        
    }

}
