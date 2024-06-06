/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import BDtables.*;
import Chain.BaseHandler;
import com.mycompany.lab3.SQLReader;
import com.mycompany.lab3.StorageDB;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Manager {
   private Storage storage;
   private StorageDB storageDB;
   private BaseHandler startHandler;
   private SQLReader SQLReader;
   private DBCreator creator;

    public Manager() {
        this.storage = new Storage();
        this.storageDB = new StorageDB();
        this.creator = new DBCreator();
        BaseHandler handlerJSON = new BaseHandler(new ReaderJSON());
        BaseHandler handlerXML = new BaseHandler(new ReaderXML());
        BaseHandler handlerYaml= new BaseHandler( new ReaderYaml());
        startHandler = handlerJSON;
        handlerJSON.setNext(handlerXML);
        handlerXML.setNext(handlerYaml);
    }
    public void useChain(File file){
        storage.addReactors( startHandler.handle(file));
    }
    public void createDB() throws SQLException{
        creator.createDB();
    }
    public ArrayList<Reactor> getInfo(){
        return storage.getList();
    }
    
    public void loadInfo() throws SQLException{
        ArrayList<Country> countries = new ArrayList<Country>(); 
        SQLReader = new SQLReader(creator.getConnection());
        ArrayList<Region> regionList = SQLReader.SQLRegionsReader();
        storageDB.addReactors(regionList);
        for (Region region: regionList){
            countries.addAll(region.getCountriesByRegion());
        }
    }
    public ArrayList<Region> getInfoDB(){
        return storageDB.getList();
    }
}
