/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.stream.*;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.*;


/**
 *
 * @author User
 */
public class ReaderXML {
      ArrayList<Reactor> reactorsList;
    public ArrayList<Reactor> ReadXML(File file) throws FileNotFoundException{
        reactorsList = new ArrayList<>();
        Reactor reactor = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try{
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));
            while (reader.hasNext()) {
                  XMLEvent nextEvent = reader.nextEvent();
                 if (nextEvent.isStartElement()) {
                      StartElement startElement = nextEvent.asStartElement();
                      switch (startElement.getName().getLocalPart()) {
                       case "Reactor" -> reactor = new Reactor();
                       case "className" -> {
                nextEvent = reader.nextEvent();
                reactor.setClassName(nextEvent.asCharacters().getData());
             }
            case "burnup" -> {
                nextEvent = reader.nextEvent();
                reactor.setBurnup(Double.parseDouble(nextEvent.asCharacters().getData()));
                          }
            case "kpd" -> {
                nextEvent = reader.nextEvent();
                reactor.setKpd(Double.parseDouble(nextEvent.asCharacters().getData()));
                          }
            case "enrichment" -> {
                nextEvent = reader.nextEvent();
                reactor.setEnrichment(Double.parseDouble(nextEvent.asCharacters().getData()));
                          }
            case "terma_capacity" -> {
                nextEvent = reader.nextEvent();
                reactor.setTermal_capacity(Double.parseDouble(nextEvent.asCharacters().getData()));
                          }
             case "electrical_capacity" -> {
                nextEvent = reader.nextEvent();
                reactor.setElectrical_capacity(Double.parseDouble(nextEvent.asCharacters().getData()));
                          }
             case "life_time" -> {
                nextEvent = reader.nextEvent();
                reactor.setLife_time(Double.parseDouble(nextEvent.asCharacters().getData()));
                          }
              case "first_load" -> {
                nextEvent = reader.nextEvent();
                reactor.setFirst_load(Double.parseDouble(nextEvent.asCharacters().getData()));
                          }   
        }
    }
    if (nextEvent.isEndElement()) {
        EndElement endElement = nextEvent.asEndElement();
        if (endElement.getName().getLocalPart().equals("Reactor")) {
            reactorsList.add(reactor);
        }
    }
}
 
        } catch (XMLStreamException exc) {
                JOptionPane.showMessageDialog(null, "Файл невозможно прочитать", "Предупреждение", JOptionPane.ERROR_MESSAGE);
        }
        
        return reactorsList;
}
}

 
