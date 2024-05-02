/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author User
 */
public class ReaderYaml implements Reader {
    ArrayList<Reactor> reactorsYaml;
    @Override
    public   ArrayList<Reactor> Read(File file) {
        reactorsYaml = new ArrayList<>();
       try{
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file.getName());
        Iterable<Object> documents = yaml.loadAll(inputStream);
        for (Object document : documents) {
            Map<String, Object> obj = (Map<String, Object>) document;
            Reactor reactor = new Reactor();
            for (String key: obj.keySet()){
                reactor.setClassName((String) obj.get("className"));
                reactor.setBurnup((double)  obj.get("burnup"));
                reactor.setKpd((double) obj.get("kpd"));
                reactor.setEnrichment((double) obj.get("enrichment"));
                reactor.setTermal_capacity((double) obj.get("termal_capacity"));
                reactor.setElectrical_capacity((double) obj.get("electrical_capacity"));
                reactor.setLife_time((double) obj.get("life_time"));
                reactor.setFirst_load((double) obj.get("first_load"));
            }
            reactorsYaml.add(reactor);
        }
          }catch(org.yaml.snakeyaml.error.YAMLException ex){
                 JOptionPane.showMessageDialog(null, "Файл невозможно прочитать", "Предупреждение", JOptionPane.ERROR_MESSAGE);
        }
        return reactorsYaml;
  
  
        
        
}
    @Override
     public String getFileType(){
        
        return "yaml";
        
    }
}



