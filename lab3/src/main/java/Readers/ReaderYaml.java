/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import static com.mycompany.lab3.Lab3.main;
import com.sun.tools.javac.Main;
import static com.sun.tools.javac.Main.main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.inspector.TagInspector;

/**
 *
 * @author User
 */
public class ReaderYaml {
    ArrayList<Reactor> reactorsYaml;
    public   ArrayList<Reactor> ReadYaml(File file) throws FileNotFoundException{
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
                 JOptionPane.showMessageDialog(null, "Файл невозмозжно прочитать", "Предупреждение", JOptionPane.ERROR_MESSAGE);
        }
        return reactorsYaml;
  
  
        
        
}
}



