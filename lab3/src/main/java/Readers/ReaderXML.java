/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.*;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.*;


/**
 *
 * @author User
 */
public class ReaderXML {
    public ReaderXML(String fileName) {
       
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try  {
             Reactors reactors = new Reactors();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("ReactorType")) {
                        Reactor reactor = new Reactor();
                        xmlEvent = reader.nextEvent();
                        while (reader.hasNext()) {
                            if (xmlEvent.isStartElement()) {
                                switch (xmlEvent.asStartElement().getName().getLocalPart()) {
                                    case "class":
                                     xmlEvent = reader.nextEvent();
                                        reactor.setClassName(xmlEvent.asCharacters().getData());
                                      break;
                                    case "burnup":
                                      xmlEvent = reader.nextEvent();
                                       reactor.setBurnup(Double.parseDouble(xmlEvent.asCharacters().getData()));
                                        break;
                                    case "kpd":
                                       xmlEvent = reader.nextEvent();
                                        reactor.setKpd(Double.parseDouble(xmlEvent.asCharacters().getData()));
                                        break;
                                    case "enrichment":
                                       xmlEvent = reader.nextEvent();
                                        reactor.setEnrichment(Double.parseDouble(xmlEvent.asCharacters().getData()));
                                        break;
                                    case "termal_capacity":
                                        xmlEvent = reader.nextEvent();
                                        reactor.setTermalCapacity(Double.parseDouble(xmlEvent.asCharacters().getData()));
                                        break;
                                    case "electrical_capacity":
                                        xmlEvent = reader.nextEvent();
                                        reactor.setElectricalCapacity(Double.parseDouble(xmlEvent.asCharacters().getData()));
                                        break;
                                    case "life_time":
                                        xmlEvent = reader.nextEvent();
                                        reactor.setLifeTime(Double.parseDouble(xmlEvent.asCharacters().getData()));
                                        break;
                                    case "first_load":
                                       xmlEvent = reader.nextEvent();
                                        reactor.setFirstLoad(Double.parseDouble(xmlEvent.asCharacters().getData()));
                                        break;
                                    default:
                                        break;
                                }
                            }
                         reactors.addReactor(reactor);
                          xmlEvent = reader.nextEvent();
                          
                        }
                     
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace(); // Обработка исключений для XML
        }
    }
}
 

    

