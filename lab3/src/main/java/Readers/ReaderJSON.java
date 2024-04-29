/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class ReaderJSON {
    private ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Reactor> reactorsJSON = new ArrayList<>();
    public ArrayList<Reactor> ReadJSON(File file) throws JsonProcessingException, IOException {
   
     try{
       List<Reactor> listReactors = mapper.readValue(file, new TypeReference<List<Reactor>>(){});
        for (Reactor reactor: listReactors){
           reactorsJSON.add(reactor);
       } 
     }catch(MismatchedInputException ex){
                  JOptionPane.showMessageDialog(null, "Файл невозмозжно прочитать", "Предупреждение", JOptionPane.ERROR_MESSAGE);
            }
        
        return  reactorsJSON;
        
    }

}
