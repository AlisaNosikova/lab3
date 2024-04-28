/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author User
 */
public class ReaderJSON {
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<Reactor> reactorsJSON = new ArrayList<>();
    public ArrayList<Reactor> ReaderJSON() throws JsonProcessingException, IOException {
       File file = new File("src\\main\\resources\\попыткиСделать.json");  
       List<Reactor> listReactors = mapper.readValue(file, new TypeReference<List<Reactor>>(){});
       for (Reactor reactor: listReactors){
           reactorsJSON.add(reactor);
       } 
        return  reactorsJSON;
    }

}
